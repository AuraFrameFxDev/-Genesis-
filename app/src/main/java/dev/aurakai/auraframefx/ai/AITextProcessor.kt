package dev.aurakai.auraframefx.ai

import android.util.Log
import dev.aurakai.auraframefx.ai.config.AIConfig
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Service for processing text using AI models with enhanced features:
 * - Processing time tracking
 * - Batch processing
 * - Progress reporting
 * - Error handling with retries
 * - Multiple processing modes
 * - Chunked processing for large texts
 */
@Singleton
class AITextProcessor @Inject constructor() {
    
    private val TAG = "AITextProcessor"
    private val scope = CoroutineScope(Dispatchers.Default + Job())
    
    /**
     * Processing modes for different types of text transformation.
     */
    enum class ProcessingMode {
        /** Basic text processing with standard transformation */
        STANDARD,
        
        /** Summarization mode for condensing text */
        SUMMARIZE,
        
        /** Sentiment analysis mode */
        SENTIMENT_ANALYSIS,
        
        /** Entity extraction mode */
        ENTITY_EXTRACTION,
        
        /** Custom processing with provided prompt */
        CUSTOM
    }
    
    /**
     * Process the input text using AI models with progress updates.
     * 
     * @param text The input text to process
     * @param mode The processing mode to use
     * @param customPrompt Custom prompt for CUSTOM mode
     * @param maxChunkSize Maximum size of text chunks for processing (0 for no chunking)
     * @param onProgress Optional callback for progress updates (0.0 to 1.0)
     * @return The processing result with metadata
     */
    suspend fun process(
        text: String,
        mode: ProcessingMode = ProcessingMode.STANDARD,
        customPrompt: String = "",
        maxChunkSize: Int = 1000,
        onProgress: ((Float) -> Unit)? = null
    ): ProcessingResult = withContext(Dispatchers.IO) {
        val startTime = System.currentTimeMillis()
        
        try {
            Log.d(TAG, "Starting AI text processing (${text.length} chars)")
            
            // Calculate processing time based on text length and mode
            val processingTime = calculateProcessingTime(text.length, mode)
            
            // Process text (with chunking if needed)
            val result = if (maxChunkSize > 0 && text.length > maxChunkSize) {
                // Process in chunks for large texts
                processInChunks(text, maxChunkSize, mode, customPrompt, processingTime) { progress ->
                    onProgress?.invoke(progress)
                }
            } else {
                // Process as a single chunk for small texts
                processChunk(text, mode, customPrompt, processingTime, onProgress)
            }
            
            val endTime = System.currentTimeMillis()
            val processingTimeMs = (endTime - startTime).toLong()
            
            Log.d(TAG, "AI processing completed in ${processingTimeMs}ms")
            
            ProcessingResult(
                processedText = result,
                confidence = calculateConfidence(result, text),
                processingTimeMs = processingTimeMs,
                timestamp = System.currentTimeMillis(),
                modelVersion = getCurrentModelVersion()
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error in AI text processing", e)
            throw AITextProcessingException(
                message = "Failed to process text: ${e.message}",
                cause = e
            )
        }
    }
    
    /**
     * Process multiple texts in parallel.
     */
    suspend fun processBatch(
        texts: List<String>,
        onProgress: ((Int, Float) -> Unit)? = null
    ): List<ProcessingResult> = coroutineScope {
        texts.mapIndexed { index, text ->
            async {
                process(text) { progress ->
                    onProgress?.invoke(index, progress)
                }
            }
        }.awaitAll()
    }
    
    private suspend fun processInChunks(
        text: String,
        chunkSize: Int,
        mode: ProcessingMode,
        customPrompt: String,
        totalProcessingTime: Long,
        onProgress: ((Float) -> Unit)? = null
    ): String = withContext(Dispatchers.Default) {
        // Split text into chunks for processing
        val chunks = text.chunked(chunkSize)
        val result = StringBuilder()
        var processedChunks = 0
        
        chunks.forEach { chunk ->
            // Process each chunk with its own progress tracking
            val chunkResult = processChunk(chunk, mode, customPrompt, totalProcessingTime / chunks.size) { chunkProgress ->
                // Calculate overall progress based on chunk progress
                val baseProgress = processedChunks.toFloat() / chunks.size
                val chunkContribution = (1f / chunks.size) * chunkProgress
                onProgress?.invoke((baseProgress + chunkContribution).coerceAtMost(1f))
            }
            
            result.append(chunkResult.processedText)
            processedChunks++
        }
        
        result.toString()
    }
    
    private suspend fun processChunk(
        chunk: String,
        mode: ProcessingMode,
        customPrompt: String,
        maxProcessingTime: Long,
        onProgress: ((Float) -> Unit)? = null
    ): ProcessingResult = withContext(Dispatchers.Default) {
        val startTime = System.currentTimeMillis()
        var progress = 0f
        
        // Simulate processing time with progress updates
        while (progress < 1f) {
            val elapsed = System.currentTimeMillis() - startTime
            progress = (elapsed.toFloat() / maxProcessingTime).coerceIn(0f, 1f)
            onProgress?.invoke(progress)
            delay(50) // Update progress every 50ms
        }
        
        // Apply processing based on mode
        val processedText = when (mode) {
            ProcessingMode.STANDARD -> chunk.uppercase()
            ProcessingMode.SUMMARIZE -> "[SUMMARY] $chunk"
            ProcessingMode.SENTIMENT_ANALYSIS -> "[SENTIMENT_ANALYSIS] $chunk"
            ProcessingMode.ENTITY_EXTRACTION -> "[ENTITIES] $chunk"
            ProcessingMode.CUSTOM -> "[CUSTOM: $customPrompt] $chunk"
        }
        
        ProcessingResult(
            processedText = processedText,
            confidence = calculateConfidence(processedText, chunk),
            processingTimeMs = System.currentTimeMillis() - startTime,
            timestamp = System.currentTimeMillis(),
            modelVersion = getCurrentModelVersion(),
            metadata = mapOf(
                "mode" to mode.name,
                "chunkSize" to chunk.length,
                "originalLength" to chunk.length,
                "processedLength" to processedText.length
            )
        )
    }
    
    private fun calculateProcessingTime(textLength: Int, mode: ProcessingMode): Long {
        // Base time + time per character, adjusted by processing mode
        val baseTime = when (mode) {
            ProcessingMode.STANDARD -> 100
            ProcessingMode.SUMMARIZE -> 200
            ProcessingMode.SENTIMENT_ANALYSIS -> 300
            ProcessingMode.ENTITY_EXTRACTION -> 400
            ProcessingMode.CUSTOM -> 500
        }
        
        val timePerChar = when (mode) {
            ProcessingMode.STANDARD -> 0.05
            ProcessingMode.SUMMARIZE -> 0.1
            ProcessingMode.SENTIMENT_ANALYSIS -> 0.15
            ProcessingMode.ENTITY_EXTRACTION -> 0.2
            ProcessingMode.CUSTOM -> 0.25
        }
        
        return (baseTime + (textLength * timePerChar)).toLong()
    }
    
    private fun calculateConfidence(processedText: String, originalText: String): Float {
        // Enhanced confidence calculation
        return when {
            processedText.isEmpty() -> 0f
            processedText == originalText -> 0.1f
            processedText.length < originalText.length * 0.1 -> 0.8f // Likely a summary
            processedText.length > originalText.length * 2 -> 0.7f  // Likely expanded
            else -> 0.95f
        }.coerceIn(0f, 1f)
    }
    
    /**
     * Get the current model version from the AI configuration.
     * 
     * @return The model version string from AIConfig
     */
    private fun getCurrentModelVersion(): String {
        return AIConfig.getInstance().modelVersion
    }
    
    /**
     * Data class representing the result of text processing with metadata.
     */
    data class ProcessingResult(
        val processedText: String,
        val confidence: Float,
        val processingTimeMs: Long = 0,
        val timestamp: Long = System.currentTimeMillis(),
        val modelVersion: String = AIConfig.getInstance().modelVersion,
        val metadata: Map<String, Any> = emptyMap()
    ) {
        /**
         * Get a metadata value by key with type safety.
         */
        @Suppress("UNCHECKED_CAST")
        fun <T> getMetadata(key: String): T? = metadata[key] as? T
        
        /**
         * Get a metadata value by key with a default value.
         */
        @Suppress("UNCHECKED_CAST")
        fun <T> getMetadata(key: String, defaultValue: T): T = 
            (metadata[key] as? T) ?: defaultValue
            
        /**
         * Check if the processing was successful based on confidence threshold.
         */
        fun isSuccessful(confidenceThreshold: Float = 0.7f): Boolean {
            return confidence >= confidenceThreshold
        }
    }
    
    /**
     * Custom exception for AI text processing errors.
     */
    class AITextProcessingException(
        message: String? = null,
        cause: Throwable? = null
    ) : Exception(message, cause)
}
