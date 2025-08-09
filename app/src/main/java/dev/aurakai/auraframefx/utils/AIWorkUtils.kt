package dev.aurakai.auraframefx.utils

import android.content.Context
import androidx.work.*
import dev.aurakai.auraframefx.workers.AITextProcessingWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Utility class for common AI work operations.
 */
@Singleton
class AIWorkUtils @Inject constructor(
    private val aiWorkManager: AIWorkManager
) {
    
    /**
     * Process text with AI and observe the result.
     */
    fun processText(
        text: String,
        priority: AIWorkManager.Priority = AIWorkManager.Priority.DEFAULT,
        requiresNetwork: Boolean = true
    ): Flow<AIWorkResult> {
        val (workId, _) = aiWorkManager.enqueueTextProcessing(
            text = text,
            priority = priority,
            requiresNetwork = requiresNetwork
        )
        
        return observeWorkResult(workId)
    }
    
    /**
     * Process multiple texts in parallel.
     */
    fun processTexts(
        texts: List<String>,
        priority: AIWorkManager.Priority = AIWorkManager.Priority.DEFAULT,
        requiresNetwork: Boolean = true
    ): List<Flow<AIWorkResult>> {
        return aiWorkManager.enqueueBatchTextProcessing(
            texts = texts,
            priority = priority,
            requiresNetwork = requiresNetwork
        ).map { (workId, _) ->
            observeWorkResult(workId)
        }
    }
    
    /**
     * Observe work result by work ID.
     */
    fun observeWorkResult(workId: UUID): Flow<AIWorkResult> {
        return aiWorkManager.observeWorkStatus(workId)
            .map { workInfo ->
                when (workInfo.state) {
                    WorkInfo.State.ENQUEUED -> AIWorkResult.Enqueued(workId)
                    WorkInfo.State.RUNNING -> {
                        val progress = workInfo.progress.getFloat(
                            AITextProcessingWorker.KEY_PROGRESS, 
                            0f
                        )
                        AIWorkResult.Running(workId, progress)
                    }
                    WorkInfo.State.SUCCEEDED -> {
                        val result = workInfo.outputData.getString(
                            AITextProcessingWorker.KEY_RESULT_TEXT
                        )
                        val confidence = workInfo.outputData.getFloat(
                            AITextProcessingWorker.KEY_CONFIDENCE, 
                            0f
                        )
                        AIWorkResult.Success(workId, result ?: "", confidence)
                    }
                    WorkInfo.State.FAILED -> {
                        val error = workInfo.outputData.getString(
                            AITextProcessingWorker.KEY_ERROR
                        )
                        AIWorkResult.Failure(workId, error ?: "Unknown error")
                    }
                    WorkInfo.State.BLOCKED -> AIWorkResult.Blocked(workId)
                    WorkInfo.State.CANCELLED -> AIWorkResult.Cancelled(workId)
                }
            }
    }
    
    /**
     * Cancel all AI work.
     */
    fun cancelAllWork() {
        aiWorkManager.cancelAllWork()
    }
    
    /**
     * Prune completed work.
     */
    fun pruneWork() {
        aiWorkManager.pruneWork()
    }
}

/**
 * Sealed class representing the result of AI work.
 */
sealed class AIWorkResult(val workId: UUID) {
    data class Enqueued(val id: UUID) : AIWorkResult(id)
    data class Running(
        val id: UUID, 
        val progress: Float
    ) : AIWorkResult(id)
    
    data class Success(
        val id: UUID,
        val result: String,
        val confidence: Float
    ) : AIWorkResult(id)
    
    data class Failure(
        val id: UUID,
        val error: String
    ) : AIWorkResult(id)
    
    data class Blocked(val id: UUID) : AIWorkResult(id)
    data class Cancelled(val id: UUID) : AIWorkResult(id)
}
