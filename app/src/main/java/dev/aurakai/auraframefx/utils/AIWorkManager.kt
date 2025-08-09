package dev.aurakai.auraframefx.utils

import android.content.Context
import android.util.Log
import androidx.work.*
import dev.aurakai.auraframefx.workers.AITextProcessingWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
     * @return The WorkRequest ID and work name
     */
    fun enqueueTextProcessing(
        text: String,
        priority: Priority = Priority.DEFAULT,
        existingWorkPolicy: ExistingWorkPolicy = ExistingWorkPolicy.REPLACE,
        requiresNetwork: Boolean = true,
        requiresCharging: Boolean = false,
        backoffPolicy: BackoffPolicy = BackoffPolicy.EXPONENTIAL
    ): Pair<UUID, String> {
        val inputData = Data.Builder()
            .putString(AITextProcessingWorker.KEY_INPUT_TEXT, text)
            .build()
            
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(if (requiresNetwork) NetworkType.CONNECTED else NetworkType.NOT_REQUIRED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiresCharging(requiresCharging)
            .build()
            
        val workRequest = OneTimeWorkRequestBuilder<AITextProcessingWorker>()
            .setInputData(inputData)
            .setConstraints(constraints)
            .setBackoffCriteria(
                backoffPolicy,
                WorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .addTag(AITextProcessingWorker.WORKER_TAG)
            .setInitialDelay(0, TimeUnit.MILLISECONDS)
            .setExpedited(priority.toExpeditedWorkPolicy())
            .build()
            
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
     */
    fun enqueueBatchTextProcessing(
        texts: List<String>,
        priority: Priority = Priority.DEFAULT,
        requiresNetwork: Boolean = true
    ): List<Pair<UUID, String>> {
        return texts.map { text ->
            enqueueTextProcessing(
                text = text,
                priority = priority,
                requiresNetwork = requiresNetwork,
                existingWorkPolicy = ExistingWorkPolicy.KEEP
            )
        }
    }
    
    /**
     * Observe work status by ID.
     */
    fun observeWorkStatus(workId: UUID): Flow<WorkInfo> {
        return workManager.getWorkInfoByIdFlow(workId)
    }
    
    /**
     * Observe work progress by ID.
     */
    fun observeWorkProgress(workId: UUID): Flow<Float> {
        return workManager.getWorkInfoByIdFlow(workId)
            .map { workInfo ->
                workInfo.progress.getFloat(AITextProcessingWorker.KEY_PROGRESS, 0f)
            }
    }
    
    /**
     * Get all work info for AI tasks.
     */
    fun getAllWorkInfo(): Flow<List<WorkInfo>> {
        return workManager.getWorkInfosByTagFlow(AITextProcessingWorker.WORKER_TAG)
    }
    
    /**
     * Cancel work by ID.
     */
    suspend fun cancelWork(workId: UUID) {
        workManager.cancelWorkById(workId)
    }
    
    /**
     * Cancel all pending AI work.
     */
    fun cancelAllWork() {
        workManager.cancelAllWorkByTag(AITextProcessingWorker.WORKER_TAG)
        Log.d(TAG, "Cancelled all AI work")
    }
    
    /**
     * Prune completed work.
     */
    fun pruneWork() {
        workManager.pruneWork()
    }
    
    /**
     * Get the status of a work request by ID.
     */
    suspend fun getWorkStatusById(workId: UUID): WorkInfo? {
        return workManager.getWorkInfoById(workId).await()
    }
    
    private fun Priority.toExpeditedWorkPolicy() = when (this) {
        Priority.HIGH -> OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST
        else -> OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST
    }
    
    /**
     * Priority levels for AI work.
     */
    enum class Priority {
        LOW,        // Background processing
        DEFAULT,    // Standard priority
        HIGH,       // User-visible work
        URGENT      // Critical work requiring immediate attention
    }
}
}
