package dev.aurakai.auraframefx.workers

import android.content.Context
import android.util.Log
import androidx.work.*
import androidx.work.workDataOf
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Base worker class for AI processing tasks with enhanced features:
 * - Progress reporting
 * - Work chaining support
 * - Better error handling
 * - Resource management
 */
abstract class AIProcessingWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    
    protected val TAG = this::class.simpleName ?: "AIProcessingWorker"
    private val isWorkActive = AtomicBoolean(true)
    private val progressState = MutableStateFlow(0f)
    private val workScope = CoroutineScope(Dispatchers.Default + Job())
    
    /**
     * Main work execution method to be implemented by subclasses.
     */
    protected abstract suspend fun doAIWork(): Result
    
    /**
     * Optional: Implement to provide progress updates
     */
    protected open suspend fun onProgressUpdate(progress: Float) {
        setProgressAsync(workDataOf(KEY_PROGRESS to progress))
    }
    
    /**
     * Optional: Implement to handle cleanup when work is cancelled
     */
    protected open suspend fun onWorkCancelled() {
        // Default implementation does nothing
    }
    
    final override suspend fun doWork(): Result {
        if (!isWorkActive.compareAndSet(false, true)) {
            Log.w(TAG, "Work already in progress")
            return Result.retry()
        }
        
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Starting AI processing work")
                
                // Set initial progress
                setProgress(workDataOf(KEY_PROGRESS to 0f))
                
                // Start progress updates in a separate coroutine
                val progressJob = workScope.launch {
                    progressState
                        .distinctUntilChanged()
                        .collect { progress ->
                            onProgressUpdate(progress)
                        }
                }
                
                // Execute the work
                val result = doAIWork()
                
                // Ensure progress is 100% when work completes
                updateProgress(1f)
                
                Log.d(TAG, "AI processing completed with result: $result")
                result
            } catch (e: CancellationException) {
                Log.w(TAG, "Work was cancelled", e)
                onWorkCancelled()
                Result.failure(workDataOf(KEY_ERROR to "Work was cancelled"))
            } catch (e: Exception) {
                Log.e(TAG, "Error during AI processing", e)
                Result.failure(
                    workDataOf(
                        KEY_ERROR to (e.message ?: "Unknown error"),
                        KEY_STACK_TRACE to e.stackTraceToString()
                    )
                )
            } finally {
                isWorkActive.set(false)
            }
        }
    }
    
    /**
     * Update the progress of the current work
     */
    protected suspend fun updateProgress(progress: Float) {
        progressState.value = progress.coerceIn(0f, 1f)
    }
    
    /**
     * Create a success result with additional output data
     */
    protected fun Result.successWithData(data: Map<String, Any>): Result {
        val outputData = workDataOf(*data.entries.map { it.key to it.value }.toTypedArray())
        return Result.success(outputData)
    }
    
    /**
     * Create a retry result with delay
     */
    protected fun Result.retryWithDelay(delay: Long): Result {
        return Result.retry().also {
            setForegroundAsync(createForegroundInfo("Retrying in ${delay}ms"))
            Thread.sleep(delay)
        }
    }
    
    override suspend fun getForegroundInfo() = createForegroundInfo("AI Processing")
    
    private fun createForegroundInfo(progress: String) = 
        ForegroundInfo(
            NOTIFICATION_ID, 
            android.app.Notification.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("AI Processing")
                .setContentText(progress)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setOngoing(true)
                .build()
        )
    
    companion object {
        const val KEY_PROGRESS = "progress"
        const val KEY_ERROR = "error"
        const val KEY_STACK_TRACE = "stack_trace"
        const val NOTIFICATION_ID = 1001
        const val NOTIFICATION_CHANNEL_ID = "ai_processing_channel"
    }
    
    /**
     * Factory for creating AIProcessingWorker instances with dependency injection.
     */
    abstract class Factory : AIProcessingWorkerFactory.ChildWorkerFactory {
        abstract override fun create(
            appContext: Context,
            params: WorkerParameters
        ): AIProcessingWorker
    }
}
