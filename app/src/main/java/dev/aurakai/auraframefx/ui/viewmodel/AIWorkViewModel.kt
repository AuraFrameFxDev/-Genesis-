package dev.aurakai.auraframefx.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aurakai.auraframefx.utils.AIWorkUtils
import dev.aurakai.auraframefx.utils.AIWorkResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * ViewModel for managing AI work operations.
 */
@HiltViewModel
class AIWorkViewModel @Inject constructor(
    private val aiWorkUtils: AIWorkUtils
) : ViewModel() {

    private val _workResults = MutableStateFlow<Map<UUID, AIWorkResult>>(emptyMap())
    val workResults: StateFlow<Map<UUID, AIWorkResult>> = _workResults.asStateFlow()

    private val _isProcessing = MutableStateFlow(false)
    val isProcessing: StateFlow<Boolean> = _isProcessing.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    /**
     * Process text using AI.
     */
    fun processText(text: String) {
        viewModelScope.launch {
            try {
                _isProcessing.value = true
                _error.value = null

                aiWorkUtils.processText(text)
                    .catch { e ->
                        _error.value = "Error processing text: ${e.message}"
                        _isProcessing.value = false
                    }
                    .collect { result ->
                        updateWorkResult(result)
                        if (result is AIWorkResult.Success || 
                            result is AIWorkResult.Failure ||
                            result is AIWorkResult.Cancelled) {
                            _isProcessing.value = false
                        }
                    }
            } catch (e: Exception) {
                _error.value = "Failed to process text: ${e.message}"
                _isProcessing.value = false
            }
        }
    }

    /**
     * Process multiple texts in parallel.
     */
    fun processTexts(texts: List<String>) {
        viewModelScope.launch {
            try {
                _isProcessing.value = true
                _error.value = null

                texts.forEach { text ->
                    aiWorkUtils.processText(text)
                        .catch { e ->
                            _error.value = "Error processing text: ${e.message}"
                        }
                        .collect { result ->
                            updateWorkResult(result)
                            // Check if all work is done
                            if (_workResults.value.values.all { 
                                it is AIWorkResult.Success || 
                                it is AIWorkResult.Failure || 
                                it is AIWorkResult.Cancelled 
                            }) {
                                _isProcessing.value = false
                            }
                        }
                }
            } catch (e: Exception) {
                _error.value = "Failed to process texts: ${e.message}"
                _isProcessing.value = false
            }
        }
    }

    /**
     * Cancel all AI work.
     */
    fun cancelAllWork() {
        viewModelScope.launch {
            aiWorkUtils.cancelAllWork()
            _workResults.value = _workResults.value.mapValues { (_, result) ->
                if (result is AIWorkResult.Running || result is AIWorkResult.Enqueued) {
                    AIWorkResult.Cancelled(result.workId)
                } else {
                    result
                }
            }
            _isProcessing.value = false
        }
    }

    /**
     * Clear all work results.
     */
    fun clearResults() {
        _workResults.value = emptyMap()
        _error.value = null
    }

    /**
     * Clear error state.
     */
    fun clearError() {
        _error.value = null
    }

    private fun updateWorkResult(result: AIWorkResult) {
        _workResults.update { current ->
            current + (result.workId to result)
        }
    }
}
