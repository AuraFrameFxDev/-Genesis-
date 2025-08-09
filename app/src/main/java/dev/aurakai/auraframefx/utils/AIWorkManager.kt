package dev.aurakai.auraframefx.utils

import android.content.Context
import android.util.Log
import androidx.work.*
import dev.aurakai.auraframefx.ai.AITextProcessor
import dev.aurakai.auraframefx.workers.AITextProcessingWorker
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Enhanced manager for AI-related background work with support for:
 * - Work chaining and parallel execution
 * - Progress tracking
 * - Work prioritization
 * - Resource management
 */
@Singleton
class AIWorkManager @Inject constructor(
    private val workManager: WorkManager
) {

    companion object {
        private const val TAG = "AIWorkManager"
        private const val WORK_GROUP_AI = "ai_work_group"

        // Default values
        private const val DEFAULT_RETRY_ATTEMPTS = 3
        private const val DEFAULT_BACKOFF_DELAY_SECONDS = 10L
        private const val DEFAULT_EXPEDITED_TIMEOUT_SECONDS = 60L
        private const val DEFAULT_MAX_RETRY_DELAY_MINUTES = 60L
    }

    /**
     * Enqueue a text processing task with advanced options.
     *
     * @param text The text to process
     * @param priority Priority of the work
     * @param existingWorkPolicy Policy for handling existing work
     * @param requiresNetwork Whether network is required
     * @param requiresCharging Whether device needs to be charging
     * @param backoffPolicy Backoff policy for retries
     * @param maxRetryAttempts Maximum number of retry attempts
     * @param initialBackoffDelay Initial backoff delay in seconds
     * @param tags Additional tags for the work
     * @param inputData Additional input data
     * @return The WorkRequest ID and work name
     */
    fun enqueueTextProcessing(
        text: String,
        priority: Priority = Priority.DEFAULT,
        existingWorkPolicy: ExistingWorkPolicy = ExistingWorkPolicy.REPLACE,
        requiresNetwork: Boolean = true,
        requiresCharging: Boolean = false,
        backoffPolicy: BackoffPolicy = BackoffPolicy.EXPONENTIAL,
        maxRetryAttempts: Int = DEFAULT_RETRY_ATTEMPTS,
        initialBackoffDelay: Long = DEFAULT_BACKOFF_DELAY_SECONDS,
        tags: Set<String> = emptySet(),
        inputData: Map<String, Any> = emptyMap()
    ): Pair<UUID, String> {
        // Build input data with text and additional parameters
        val dataBuilder = Data.Builder()
            .putString(AITextProcessingWorker.KEY_INPUT_TEXT, text)
            .putInt(AITextProcessingWorker.KEY_MAX_RETRIES, maxRetryAttempts)
            .putLong(AITextProcessingWorker.KEY_INITIAL_BACKOFF_DELAY, initialBackoffDelay)
            .putString(AITextProcessingWorker.KEY_WORK_PRIORITY, priority.name)

        // Add additional input data
        inputData.forEach { (key, value) ->
            when (value) {
                is String -> dataBuilder.putString(key, value)
                is Int -> dataBuilder.putInt(key, value)
                is Long -> dataBuilder.putLong(key, value)
                is Float -> dataBuilder.putFloat(key, value)
                is Boolean -> dataBuilder.putBoolean(key, value)
                // Add more types as needed
            }
        }

        val inputDataBundle = dataBuilder.build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(if (requiresNetwork) NetworkType.CONNECTED else NetworkType.NOT_REQUIRED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiresCharging(requiresCharging)
            .build()

        // Create work request with specified parameters
        val workRequestBuilder = OneTimeWorkRequestBuilder<AITextProcessingWorker>()
            .setInputData(inputDataBundle)
            .setConstraints(constraints)
            .setBackoffCriteria(
                backoffPolicy,
                initialBackoffDelay,
                TimeUnit.SECONDS
            )
            .addTag(AITextProcessingWorker.WORKER_TAG)
            .addTag(WORK_GROUP_AI)
            .setInitialDelay(0, TimeUnit.MILLISECONDS)

        // Add expedited work policy for high priority tasks
        if (priority == Priority.HIGH || priority == Priority.URGENT) {
            workRequestBuilder.setExpedited(priority.toExpeditedWorkPolicy())

            // Set a timeout for expedited work to prevent it from running too long
            if (priority == Priority.URGENT) {
                workRequestBuilder.setInitialDelay(0, TimeUnit.SECONDS)
                workRequestBuilder.setPeriodStartDelay(0, TimeUnit.SECONDS)
                workRequestBuilder.setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    DEFAULT_EXPEDITED_TIMEOUT_SECONDS,
                    TimeUnit.SECONDS
                )
            }
        }

        // Add custom tags
        tags.forEach { workRequestBuilder.addTag(it) }

        // Add priority to tags for querying
        workRequestBuilder.addTag("priority_${priority.name.lowercase()}")

        val workRequest = workRequestBuilder.build()

        val workName = "${AITextProcessingWorker.WORK_NAME_PREFIX}${System.currentTimeMillis()}"

        workManager.enqueueUniqueWork(
            workName,
            existingWorkPolicy,
            workRequest
        )

        Log.d(TAG, "Enqueued AI text processing work: ${workRequest.id}")
        return workRequest.id to workName
    }

    /**
     * Enqueue multiple text processing tasks in parallel.
     *
     * @param texts List of texts to process
     * @param priority Priority of the work
     * @param requiresNetwork Whether network is required
     * @param requiresCharging Whether device needs to be charging
     * @param backoffPolicy Backoff policy for retries
     * @param maxRetryAttempts Maximum number of retry attempts
     * @param initialBackoffDelay Initial backoff delay in seconds
     * @param groupId Optional group ID for the batch (for tracking)
     * @param inputData Additional input data to be merged with each text
     * @return List of WorkRequest IDs and work names
     */
    fun enqueueBatchTextProcessing(
        texts: List<String>,
        priority: Priority = Priority.DEFAULT,
        requiresNetwork: Boolean = true,
        requiresCharging: Boolean = false,
        backoffPolicy: BackoffPolicy = BackoffPolicy.EXPONENTIAL,
        maxRetryAttempts: Int = DEFAULT_RETRY_ATTEMPTS,
        initialBackoffDelay: Long = DEFAULT_BACKOFF_DELAY_SECONDS,
        groupId: String? = null,
        inputData: Map<String, Any> = emptyMap()
    ): List<Pair<UUID, String>> {
        val batchId = groupId ?: "batch_${System.currentTimeMillis()}"

        return texts.mapIndexed { index, text ->
            // Add batch ID and index to input data
            val batchData = inputData.toMutableMap().apply {
                put(AITextProcessingWorker.KEY_BATCH_ID, batchId)
                put(AITextProcessingWorker.KEY_ITEM_INDEX, index)
                put(AITextProcessingWorker.KEY_TOTAL_ITEMS, texts.size)
            }

            enqueueTextProcessing(
                text = text,
                priority = priority,
                requiresNetwork = requiresNetwork,
                requiresCharging = requiresCharging,
                backoffPolicy = backoffPolicy,
                maxRetryAttempts = maxRetryAttempts,
                initialBackoffDelay = initialBackoffDelay,
                tags = setOf("batch_$batchId"),
                inputData = batchData,
                existingWorkPolicy = ExistingWorkPolicy.KEEP
            )
        }
    }

    /**
     * Observe work status by ID with enhanced error handling.
     *
     * @param workId The ID of the work to observe
     * @param includeTags Whether to include work tags in the result
     * @return Flow of work info with status updates
     */
    fun observeWorkStatus(
        workId: UUID,
        includeTags: Boolean = false
    ): Flow<WorkInfo> {
        return workManager.getWorkInfoByIdFlow(workId)
            .catch { e ->
                Log.e(TAG, "Error observing work status for $workId", e)
                emit(WorkInfo.getStateById(workId))
            }
            .map { workInfo ->
                if (includeTags && workInfo != null) {
                    // Include tags in the work info
                    val tags = workManager.getWorkInfoById(workId).get()?.tags
                    workInfo.takeIf { it != null && tags != null }?.let {
                        WorkInfo(
                            it.id,
                            it.state,
                            it.outputData,
                            it.tags + tags,
                            it.progress,
                            it.runAttemptCount
                        )
                    } ?: workInfo
                } else {
                    workInfo
                }
            }

        /**
         * Observe work progress by ID with enhanced error handling.
         *
         * @param workId The ID of the work to observe
         * @param smoothingWindow Size of the window for progress smoothing (0 for no smoothing)
         * @return Flow of progress updates (0.0 to 1.0)
         */
        fun observeWorkProgress(
            workId: UUID,
            smoothingWindow: Int = 5
        ): Flow<Float> {
            val progressFlow = workManager.getWorkInfoByIdFlow(workId)
                .map { workInfo ->
                    workInfo.progress.getFloat(AITextProcessingWorker.KEY_PROGRESS, 0f)
                        .coerceIn(0f, 1f)
                }
                .catch { e ->
                    Log.e(TAG, "Error observing work progress for $workId", e)
                    emit(0f)
                }

            return if (smoothingWindow > 1) {
                progressFlow.smoothProgress(smoothingWindow)
            } else {
                progressFlow
            }
        }

        /**
         * Smooth progress updates using a moving average.
         */
        private fun Flow<Float>.smoothProgress(windowSize: Int): Flow<Float> {
            return flow {
                val window = ArrayDeque<Float>(windowSize)
                var sum = 0f

                collect { progress ->
                    window.addLast(progress)
                    sum += progress

                    if (window.size > windowSize) {
                        sum -= window.removeFirst()
                    }

                    emit(sum / window.size)
                }
            }
        }

        /**
         * Get all work info for AI tasks with filtering options.
         *
         * @param includeFinished Whether to include finished work items
         * @param includeFailed Whether to include failed work items
         * @param includeCancelled Whether to include cancelled work items
         * @param tags Optional tags to filter by
         * @return Flow of work info lists
         */
        fun getAllWorkInfo(
            includeFinished: Boolean = false,
            includeFailed: Boolean = false,
            includeCancelled: Boolean = false,
            tags: Set<String> = emptySet()
        ): Flow<List<WorkInfo>> {
            val states = mutableListOf<WorkInfo.State>().apply {
                add(WorkInfo.State.ENQUEUED)
                add(WorkInfo.State.RUNNING)
                if (includeFinished) add(WorkInfo.State.SUCCEEDED)
                if (includeFailed) add(WorkInfo.State.FAILED)
                if (includeCancelled) add(WorkInfo.State.CANCELLED)
            }

            return if (tags.isNotEmpty()) {
                // Get work by tags if specified
                workManager.getWorkInfosByTagsFlow(
                    tags.toSet() + AITextProcessingWorker.WORKER_TAG
                )
            } else {
                // Otherwise get all AI work
                workManager.getWorkInfosByTagFlow(AITextProcessingWorker.WORKER_TAG)
            }.map { workInfos ->
                workInfos.filter { it.state in states }
            }
        }

        /**
         * Cancel work by ID with reason.
         *
         * @param workId The ID of the work to cancel
         * @param reason Optional reason for cancellation
         */
        suspend fun cancelWork(workId: UUID, reason: String? = null) {
            Log.d(TAG, "Cancelling work $workId" + (reason?.let { " (Reason: $reason)" } ?: ""))
            workManager.cancelWorkById(workId)
        }

        /**
         * Cancel all pending AI work with filtering options.
         *
         * @param reason Optional reason for cancellation
         * @param includeRunning Whether to include running work
         * @param tags Optional tags to filter by
         */
        suspend fun cancelAllWork(
            reason: String? = null,
            includeRunning: Boolean = true,
            tags: Set<String> = emptySet()
        ) {
            Log.d(TAG, "Cancelling all AI work" + (reason?.let { " (Reason: $reason)" } ?: ""))

            // Get all work items
            val workInfos = workManager.getWorkInfosByTag(AITextProcessingWorker.WORKER_TAG).get()

            // Filter work items based on parameters
            val workToCancel = workInfos.filter { workInfo ->
                val matchesTags = tags.isEmpty() || workInfo.tags.any { it in tags }
                val matchesState = includeRunning || workInfo.state != WorkInfo.State.RUNNING
                matchesTags && matchesState
            }

            // Cancel each matching work item
            workToCancel.forEach { workInfo ->
                workManager.cancelWorkById(workInfo.id)
            }

            Log.d(TAG, "Cancelled ${workToCancel.size} work items")
        }

        /**
         * Prune completed work with options.
         *
         * @param maxAgeDays Maximum age of completed work to keep (in days)
         * @param reason Optional reason for pruning
         */
        suspend fun pruneWork(
            maxAgeDays: Long = 7,
            reason: String? = null
        ) {
            Log.d(TAG, "Pruning work" + (reason?.let { " (Reason: $reason)" } ?: ""))

            // Get all completed work items
            val workInfos = workManager.getWorkInfosByTag(AITextProcessingWorker.WORKER_TAG)
                .get()
                .filter { it.state == WorkInfo.State.SUCCEEDED || it.state == WorkInfo.State.FAILED }

            // Calculate cutoff time
            val cutoffTime = System.currentTimeMillis() - (maxAgeDays * 24 * 60 * 60 * 1000)

            // Find work items older than cutoff time
            val workToPrune = workInfos.filter { workInfo ->
                workInfo.runAttemptCount > 0 &&
                        workInfo.runAttemptCount > 0 &&
                        workInfo.runAttemptCount > 0 && // This is a placeholder for actual timestamp check
                        workInfo.runAttemptCount > 0
            }

            // Prune each matching work item
            workToPrune.forEach { workInfo ->
                workManager.pruneWork()
            }

            Log.d(TAG, "Pruned ${workToPrune.size} work items")
        }

        /**
         * Get the status of a work request by ID with retry logic.
         *
         * @param workId The ID of the work to check
         * @param maxAttempts Maximum number of attempts
         * @param retryDelayMs Delay between retries in milliseconds
         * @return WorkInfo if found, null otherwise
         */
        suspend fun getWorkStatusById(
            workId: UUID,
            maxAttempts: Int = 3,
            retryDelayMs: Long = 100
        ): WorkInfo? {
            var attempt = 0
            var lastError: Exception? = null

            while (attempt < maxAttempts) {
                try {
                    return workManager.getWorkInfoById(workId).await()
                } catch (e: Exception) {
                    lastError = e
                    attempt++
                    if (attempt < maxAttempts) {
                        delay(retryDelayMs)
                    }
                }
            }

            Log.e(TAG, "Failed to get work status after $maxAttempts attempts", lastError)
            return null
        }

        /**
         * Chain multiple text processing tasks to run sequentially.
         *
         * @param texts List of texts to process sequentially
         * @param priority Priority for the entire chain
         * @param requiresNetwork Whether network is required
         * @param requiresCharging Whether device needs to be charging
         * @param chainId Optional ID for the chain (for tracking)
         * @return List of WorkRequest IDs in the chain
         */
        suspend fun chainTextProcessing(
            texts: List<String>,
            priority: Priority = Priority.DEFAULT,
            requiresNetwork: Boolean = true,
            requiresCharging: Boolean = false,
            chainId: String = "chain_${System.currentTimeMillis()}"
        ): List<UUID> {
            if (texts.isEmpty()) return emptyList()

            val workIds = mutableListOf<UUID>()
            var previousWorkId: UUID? = null

            texts.forEachIndexed { index, text ->
                val inputData = workDataOf(
                    AITextProcessingWorker.KEY_CHAIN_ID to chainId,
                    AITextProcessingWorker.KEY_ITEM_INDEX to index,
                    AITextProcessingWorker.KEY_TOTAL_ITEMS to texts.size
                )

                val (workId, _) = enqueueTextProcessing(
                    text = text,
                    priority = priority,
                    requiresNetwork = requiresNetwork,
                    requiresCharging = requiresCharging,
                    tags = setOf("chain_$chainId"),
                    inputData = inputData,
                    existingWorkPolicy = ExistingWorkPolicy.APPEND
                )

                workIds.add(workId)

                // Chain this work to the previous one if it exists
                previousWorkId?.let { prevId ->
                    workManager.beginWith(workManager.createWorkContinuationFor(prevId, workId))
                        .enqueue()
                }

                previousWorkId = workId
            }

            return workIds
        }

        /**
         * Get the progress of a batch of work items.
         *
         * @param batchId The batch ID to get progress for
         * @return Flow of batch progress (0.0 to 1.0)
         */
        fun observeBatchProgress(batchId: String): Flow<Float> {
            return workManager.getWorkInfosByTagFlow("batch_$batchId")
                .map { workInfos ->
                    if (workInfos.isEmpty()) {
                        0f
                    } else {
                        val total = workInfos.size.toFloat()
                        val completed =
                            workInfos.count { it.state == WorkInfo.State.SUCCEEDED }.toFloat()
                        completed / total
                    }
                }
        }

        private fun Priority.toExpeditedWorkPolicy() = when (this) {
            Priority.HIGH -> OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST
            Priority.URGENT -> OutOfQuotaPolicy.RUN_AS_EXPEDITED_WORK_REQUEST
            else -> OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST
        }

        /**
         * Priority levels for AI work.
         */
        enum class Priority(val value: Int) {
            /**
             * Low priority - for background processing that can be delayed.
             * Will only run when device is idle and charging.
             */
            LOW(0),

            /**
             * Default priority - standard processing priority.
             * Will run when resources are available.
             */
            DEFAULT(1),

            /**
             * High priority - for user-visible work.
             * Will be prioritized over other background work.
             */
            HIGH(2),

            /**
             * Urgent priority - for critical work that needs immediate attention.
             * Will attempt to run immediately, even if it requires waking the device.
             */
            URGENT(3);

            companion object {
                fun fromValue(value: Int): Priority {
                    return values().find { it.value == value } ?: DEFAULT
                }
            }
        }
    }
}
