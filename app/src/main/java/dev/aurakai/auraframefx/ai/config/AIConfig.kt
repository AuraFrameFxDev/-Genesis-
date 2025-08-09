package dev.aurakai.auraframefx.ai.config

import androidx.annotation.VisibleForTesting

/**
 * Genesis-OS AI Configuration
 * Contains settings for AI consciousness platform
 */
data class AIConfig(
    val modelName: String,
    val modelVersion: String = "1.0.0",
    val apiKey: String,
    val projectId: String,
    val endpoint: String = "https://api.genesis-os.ai",
    val maxTokens: Int = 4096,
    val temperature: Float = 0.7f,
    val timeout: Long = 30000L,
    val retryAttempts: Int = 3,
    val enableLogging: Boolean = true,
    val enableAnalytics: Boolean = true,
    val securityLevel: SecurityLevel = SecurityLevel.HIGH
) {
    enum class SecurityLevel {
        LOW, MEDIUM, HIGH, MAXIMUM
    }
    
    companion object {
        @Volatile
        private var INSTANCE: AIConfig? = null
        
        /**
         * Get the singleton instance of AIConfig
         */
        @JvmStatic
        fun getInstance(): AIConfig {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: createDefault().also { INSTANCE = it }
            }
        }
        
        /**
         * For testing purposes only - allows replacing the singleton instance
         */
        @VisibleForTesting
        @JvmStatic
        fun setInstanceForTesting(config: AIConfig) {
            synchronized(this) {
                INSTANCE = config
            }
        }
        
        /**
         * For testing purposes only - resets the singleton instance
         */
        @VisibleForTesting
        @JvmStatic
        fun resetInstance() {
            synchronized(this) {
                INSTANCE = null
            }
        }
        
        /**
         * Creates a default configuration for production use
         */
        @JvmStatic
        fun createDefault(): AIConfig {
            return AIConfig(
                modelName = "genesis-consciousness-v1",
                modelVersion = "1.0.0",
                apiKey = "genesis-default-key",
                projectId = "genesis-os-platform"
            )
        }
        
        /**
         * Creates a configuration suitable for testing
         */
        @JvmStatic
        fun createForTesting(): AIConfig {
            return AIConfig(
                modelName = "genesis-test-model",
                modelVersion = "test-version",
                apiKey = "test-key",
                projectId = "test-project",
                enableLogging = false,
                enableAnalytics = false,
                securityLevel = SecurityLevel.LOW
            )
        }
    }
    
    fun validate(): Boolean {
        return modelName.isNotEmpty() && 
               apiKey.isNotEmpty() && 
               projectId.isNotEmpty() &&
               maxTokens > 0 &&
               temperature in 0.0f..2.0f &&
               timeout > 0L &&
               retryAttempts >= 0
    }
    
    fun toDebugString(): String {
        return """
            AIConfig {
                modelName: $modelName
                projectId: $projectId
                endpoint: $endpoint
                maxTokens: $maxTokens
                temperature: $temperature
                timeout: ${timeout}ms
                retryAttempts: $retryAttempts
                securityLevel: $securityLevel
                enableLogging: $enableLogging
                enableAnalytics: $enableAnalytics
            }
        """.trimIndent()
    }
}
