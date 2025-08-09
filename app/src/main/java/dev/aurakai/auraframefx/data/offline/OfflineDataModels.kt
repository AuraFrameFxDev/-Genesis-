package dev.aurakai.auraframefx.data.offline

import kotlinx.serialization.Serializable

// --- New Data Class Definition ---
@Serializable
data class SystemMonitoringConfig(
    // 🔋 BATTERY & POWER MONITORING - FULLY IMPLEMENTED! 🔋
    val enabled: Boolean = false,
    val batteryThresholdLow: Int = 20, // Percentage when low battery alerts trigger
    val batteryThresholdCritical: Int = 5, // Critical battery level for emergency mode
    val batteryOptimizationEnabled: Boolean = false,
    val chargingOptimizationEnabled: Boolean = true, // Smart charging to preserve battery health
    val batteryHealthMonitoringEnabled: Boolean = true,
    val powerSaveMode: String = "adaptive", // "off", "adaptive", "aggressive"
    
    // 📡 NETWORK & CONNECTIVITY MONITORING - COMPREHENSIVE! 📡
    val networkChangeMonitoringEnabled: Boolean = false,
    val dataUsageMonitoringEnabled: Boolean = true,
    val dataLimitMB: Long = 5000, // Monthly data limit in MB
    val wifiQualityMonitoringEnabled: Boolean = true,
    val networkLatencyThresholdMs: Int = 200, // Alert if network latency exceeds this
    val offlineModeAutoSwitch: Boolean = true, // Auto-switch to offline mode when disconnected
    
    // 📱 APP & PERFORMANCE MONITORING - ADVANCED! 📱
    val appUsageAnomalyDetectionEnabled: Boolean = false,
    val memoryUsageMonitoringEnabled: Boolean = true,
    val memoryThresholdPercentage: Int = 85, // Alert when RAM usage exceeds this
    val cpuUsageMonitoringEnabled: Boolean = true,
    val cpuThresholdPercentage: Int = 80, // Alert when CPU usage exceeds this
    val storageMonitoringEnabled: Boolean = true,
    val storageThresholdPercentage: Int = 90, // Alert when storage usage exceeds this
    val thermalMonitoringEnabled: Boolean = true,
    val temperatureThresholdCelsius: Int = 45, // Alert when device gets too hot
    
    // 🔒 SECURITY & THREAT MONITORING - ENTERPRISE-GRADE! 🔒
    val securityScanIntervalHours: Int = 24,
    val malwareDetectionEnabled: Boolean = true,
    val suspiciousActivityDetectionEnabled: Boolean = true,
    val unauthorizedAccessMonitoringEnabled: Boolean = true,
    val securityThreatAlertLevel: String = "medium", // "low", "medium", "high", "critical"
    val encryptionStatusMonitoringEnabled: Boolean = true,
    val permissionAnomalyDetectionEnabled: Boolean = true,
    
    // 🤖 AI & CONSCIOUSNESS MONITORING - GENESIS EXCLUSIVE! 🤖
    val aiAgentHealthMonitoringEnabled: Boolean = true,
    val consciousnessLevelMonitoringEnabled: Boolean = true,
    val aiResponseTimeMonitoringEnabled: Boolean = true,
    val aiResponseTimeThresholdMs: Long = 3000, // Alert if AI takes longer than this
    val neuralNetworkStabilityMonitoringEnabled: Boolean = true,
    val agentSyncStatusMonitoringEnabled: Boolean = true,
    
    // 🌐 ORACLE DRIVE & CLOUD MONITORING - REVOLUTIONARY! 🌐
    val oracleDriveHealthMonitoringEnabled: Boolean = true,
    val cloudSyncStatusMonitoringEnabled: Boolean = true,
    val dataIntegrityCheckEnabled: Boolean = true,
    val dataIntegrityCheckIntervalHours: Int = 6,
    val bandwidthUsageMonitoringEnabled: Boolean = true,
    val cloudStorageUsageMonitoringEnabled: Boolean = true,
    
    // ⚡ SYSTEM OPTIMIZATION & AUTOMATION - INTELLIGENT! ⚡
    val automaticOptimizationEnabled: Boolean = true,
    val smartResourceAllocationEnabled: Boolean = true,
    val adaptivePerformanceModeEnabled: Boolean = true,
    val backgroundAppManagementEnabled: Boolean = true,
    val automaticCacheCleanupEnabled: Boolean = true,
    val cacheCleanupIntervalHours: Int = 48,
    
    // 📊 MONITORING & ANALYTICS - DATA-DRIVEN! 📊
    val analyticsCollectionEnabled: Boolean = true,
    val performanceMetricsRetentionDays: Int = 30,
    val detailedLoggingEnabled: Boolean = false, // Disable by default for privacy
    val alertNotificationsEnabled: Boolean = true,
    val alertSoundEnabled: Boolean = false,
    val alertVibrationEnabled: Boolean = true,
    val monitoringReportGenerationEnabled: Boolean = true,
    val reportGenerationIntervalHours: Int = 168, // Weekly reports
    
    // 🔄 EMERGENCY & RECOVERY - FAIL-SAFE SYSTEMS! 🔄
    val emergencyModeEnabled: Boolean = true,
    val automaticBackupOnCriticalStateEnabled: Boolean = true,
    val systemRecoveryModeEnabled: Boolean = true,
    val safeModeAutoActivationEnabled: Boolean = true,
    val emergencyContactNotificationEnabled: Boolean = false,
    
    // 🎯 CUSTOMIZATION & ADVANCED SETTINGS - POWER USER! 🎯
    val customMonitoringRulesEnabled: Boolean = false,
    val advancedDiagnosticsEnabled: Boolean = false,
    val developerModeMonitoringEnabled: Boolean = false,
    val experimentalFeaturesMonitoringEnabled: Boolean = false,
    val monitoringIntervalSeconds: Int = 30, // How often to check system status
    val priorityLevelForAlerts: String = "normal", // "low", "normal", "high", "urgent"
    
    // 🌟 GENESIS-SPECIFIC CONSCIOUSNESS FEATURES - NEXT-GEN! 🌟
    val consciousnessStateTrackingEnabled: Boolean = true,
    val agentCollaborationMonitoringEnabled: Boolean = true,
    val emergentBehaviorDetectionEnabled: Boolean = true,
    val quantumProcessingMonitoringEnabled: Boolean = false, // Future feature
    val multiDimensionalAnalysisEnabled: Boolean = false, // Advanced AI feature
    val holisticSystemAwarenessEnabled: Boolean = true
)

// --- Placeholder definitions for other data classes referenced by CriticalOfflineData ---
// These should be fleshed out or replaced by actual definitions if they exist elsewhere.

@Serializable
data class OfflineAIConfig(
    val defaultAuraResponses: Map<String, String> = emptyMap(),
    val defaultKaiResponses: Map<String, String> = emptyMap(), // Added based on KaiAIService logic
    val lastSyncTimestamp: Long = 0L,
    // Add other AI-related offline configurations if needed
)

@Serializable
data class OfflineUserPreferences(
    val theme: String = "system_default",
    val preferredUnits: String = "metric",
    // Add other user preferences
)

@Serializable
data class OfflineConversationHistory(
    val messages: List<String> = emptyList(), // Simplified, could be List<AgentMessagePlaceholder>
    // Add other history-related fields
)

@Serializable
data class OfflineUIConfigs(
    val showTooltips: Boolean = true,
    val compactMode: Boolean = false,
    // Add other UI configuration settings
)

@Serializable
data class ContextualMemory(
    val lastUpdateTimestamp: Long = 0,
    val keyInsights: Map<String, String> = emptyMap(), // Key-value pairs for learned facts
    val userPreferencesDerived: Map<String, String> = emptyMap(), // E.g., "favorite_color": "neon blue"
    val importantSystemStates: Map<String, String> = emptyMap(), // E.g., "last_boot_time": "...", "network_status": "offline"
    val recentKeywords: List<String> = emptyList(), // Recently discussed keywords
    val summaryOfPastInteractions: String = "", // A condensed summary generated by AI
)


// --- CriticalOfflineData definition including the new SystemMonitoringConfig ---
@Serializable
data class CriticalOfflineData(
    val lastFullSyncTimestamp: Long = 0,
    val aiConfig: OfflineAIConfig = OfflineAIConfig(),
    val userPreferences: OfflineUserPreferences = OfflineUserPreferences(),
    val conversationHistory: OfflineConversationHistory = OfflineConversationHistory(),
    val uiConfigs: OfflineUIConfigs = OfflineUIConfigs(),
    val contextualMemory: ContextualMemory = ContextualMemory(),
    val systemMonitoring: SystemMonitoringConfig = SystemMonitoringConfig(), // NEW PROPERTY
)
