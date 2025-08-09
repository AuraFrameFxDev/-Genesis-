package dev.aurakai.auraframefx.workers

import android.content.Context
import android.util.Log
import androidx.hilt.assisted.Assisted
import androidx.hilt.assisted.AssistedInject
import androidx.work.*
import dev.aurakai.auraframefx.ai.AITextProcessor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Worker for processing text using AI models with enhanced features:
 * - Progress tracking
 * - Automatic retries with backoff
 * - Resource cleanup
 * - Detailed error reporting
 */
class AITextProcessingWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val aiTextProcessor: AITextProcessor
) : AIProcessingWorker(appContext, workerParams) {

    private var currentJob: kotlinx.coroutines.Job? = null
    
    override suspend fun doAIWork(): Result {
        val text = inputData.getString(KEY_INPUT_TEXT) ?: run {
            Log.e(TAG, "No input text provided")
            return Result.failure(
                workDataOf(KEY_ERROR to "No input text provided")
            )
        }

        // Set up progress tracking
        var progress = 0f
        
        // Simulate progress for long-running operations
        val progressJob = launchProgressUpdater()
        currentJob = progressJob
        
        return try {
            Log.d(TAG, "Starting text processing: ${text.take(50)}...")
            
            // Process text using the AI processor with progress updates
            val result = processTextWithProgress(text) { currentProgress ->
                progress = currentProgress
                updateProgress(progress)
            }
            
            // Ensure we reach 100% progress
            updateProgress(1f)
            
            // Return success with the processed result
            Result.success(
                workDataOf(
                    KEY_RESULT_TEXT to result.processedText,
                    KEY_CONFIDENCE to result.confidence,
                    KEY_PROCESSING_TIME_MS to result.processingTimeMs
                )
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error processing text", e)
            Result.failure(
                workDataOf(
                    KEY_ERROR to (e.message ?: "Unknown error"),
                    KEY_STACK_TRACE to e.stackTraceToString(),
                    KEY_PROGRESS to progress
                )
            )
        } finally {
            progressJob.cancel()
            currentJob = null
        }
    }
    
    private suspend fun processTextWithProgress(
        text: String,
        onProgress: (Float) -> Unit
    ): AITextProcessor.ProcessingResult {
        return flow {
            // Simulate processing steps
            emit(0.1f) // Started
            
            // Process text in chunks to show progress
            val chunks = text.chunked(text.length / 10).toList()
            var processedText = ""
            
            chunks.forEachIndexed { index, chunk ->
                // Simulate processing time
                delay(100)
                processedText += chunk
                
                // Update progress
                val progress = 0.1f + (index.toFloat() / chunks.size) * 0.8f
                emit(progress)
            }
            
            // Final processing
            delay(200)
            emit(0.95f)
            
            // Get the final result
            val result = aiTextProcessor.process(processedText)
            emit(1.0f)
            
            result
        }.collect { progress ->
            when (progress) {
                is Float -> onProgress(progress)
                is AITextProcessor.ProcessingResult -> return@collect progress
            }
        } as AITextProcessor.ProcessingResult
    }
    
    private fun launchProgressUpdater() = workScope.launch {
        while (isActive) {
            delay(1000) // Update progress every second
            setProgressAsync(workDataOf(KEY_PROGRESS to 0.01f))
        }
    }
    
    override suspend fun onWorkCancelled() {
        Log.w(TAG, "Text processing work was cancelled")
        currentJob?.cancel()
        currentJob = null
    }

    /**
     * Factory for creating AITextProcessingWorker instances with dependency injection.
     */
    class Factory @Inject constructor(
        private val aiTextProcessor: AITextProcessor
    ) : AIProcessingWorker.Factory() {
        override fun create(
            appContext: Context,
            params: WorkerParameters
        ): AITextProcessingWorker {
            return AITextProcessingWorker(appContext, params, aiTextProcessor)
        }
    }

    companion object {
        // Input data keys
        const val KEY_INPUT_TEXT = "input_text"
        
        // Output data keys
        const val KEY_RESULT_TEXT = "result_text"
        const val KEY_CONFIDENCE = "confidence"
        const val KEY_PROCESSING_TIME_MS = "processing_time_ms"
        
        // Worker configuration
        const val WORKER_TAG = "ai_text_processing_worker"
        const val WORK_NAME_PREFIX = "ai_text_processing_"
        
        // Constraints
        val WORKER_CONSTRAINTS = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build()
            
        // Backoff policy
        val BACKOFF_POLICY = Pair(
            BackoffPolicy.EXPONENTIAL,
            WorkRequest.MIN_BACKOFF_MILLIS
        )
    }
}
