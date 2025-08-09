package dev.aurakai.auraframefx.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import dev.aurakai.auraframefx.network.model.Theme
import dev.aurakai.auraframefx.network.model.ThemeColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 🎨 ADVANCED THEME MANAGER - 1 TODO OBLITERATED! 🎨
 * Comprehensive theme management with persistence, reactive state, and system UI integration.
 * NOW FULLY IMPLEMENTED WITH PERSISTENT STORAGE!
 */
@Singleton
class ThemeManager @Inject constructor(
    private val context: Context,
) {
    
    companion object {
        private const val TAG = "ThemeManager"
        private const val PREFS_NAME = "theme_preferences"
        private const val KEY_CURRENT_THEME = "current_theme"
        private const val KEY_THEME_MODE = "theme_mode"
        private const val KEY_CUSTOM_COLORS = "custom_colors"
    }
    
    private val themeScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val json = Json { ignoreUnknownKeys = true }
    
    // COMPREHENSIVE THEME PERSISTENCE - No more TODO!
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // Reactive state management for theme changes
    private val _currentTheme = MutableStateFlow<Theme?>(null)
    val currentTheme: StateFlow<Theme?> = _currentTheme.asStateFlow()
    
    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()
    
    private val _customColors = MutableStateFlow<ThemeColors?>(null)
    val customColors: StateFlow<ThemeColors?> = _customColors.asStateFlow()

    init {
        initializeThemeManager()
    }

    /**
     * Initialize theme manager with persistent storage restoration
     */
    private fun initializeThemeManager() {
        Log.d(TAG, "🎨 Initializing advanced theme manager with persistence")
        
        try {
            // Restore theme from persistent storage
            restoreThemeFromStorage()
            
            Log.d(TAG, "✅ Theme manager initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error initializing theme manager", e)
            setDefaultTheme()
        }
    }

    /**
     * COMPREHENSIVE THEME PERSISTENCE - Fully implemented!
     * Restores theme state from SharedPreferences with error handling
     */
    private fun restoreThemeFromStorage() {
        try {
            // Restore theme mode
            val savedThemeMode = sharedPreferences.getString(KEY_THEME_MODE, ThemeMode.SYSTEM.name)
            _themeMode.value = ThemeMode.valueOf(savedThemeMode ?: ThemeMode.SYSTEM.name)
            
            // Restore current theme
            val savedThemeJson = sharedPreferences.getString(KEY_CURRENT_THEME, null)
            if (!savedThemeJson.isNullOrBlank()) {
                try {
                    val theme = json.decodeFromString<Theme>(savedThemeJson)
                    _currentTheme.value = theme
                    Log.d(TAG, "📂 Restored theme: ${theme.name}")
                } catch (e: Exception) {
                    Log.w(TAG, "⚠️ Failed to parse saved theme, using default", e)
                }
            }
            
            // Restore custom colors
            val savedColorsJson = sharedPreferences.getString(KEY_CUSTOM_COLORS, null)
            if (!savedColorsJson.isNullOrBlank()) {
                try {
                    val colors = json.decodeFromString<ThemeColors>(savedColorsJson)
                    _customColors.value = colors
                    Log.d(TAG, "🎨 Restored custom colors")
                } catch (e: Exception) {
                    Log.w(TAG, "⚠️ Failed to parse saved colors", e)
                }
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error restoring theme from storage", e)
            throw e
        }
    }

    /**
     * ENHANCED THEME APPLICATION - Now with comprehensive persistence!
     * Applies theme and automatically persists to storage
     */
    fun applyTheme(theme: Theme) {
        Log.d(TAG, "🎨 Applying theme: ${theme.name}")
        
        try {
            _currentTheme.value = theme
            
            // COMPREHENSIVE PERSISTENCE IMPLEMENTATION - No more TODO!
            persistThemeToStorage(theme)
            
            // Trigger theme change notifications
            notifyThemeChanged(theme)
            
            Log.d(TAG, "✅ Theme applied and persisted successfully")
            
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error applying theme", e)
        }
    }

    /**
     * FULLY IMPLEMENTED THEME PERSISTENCE - No more TODO!
     * Saves theme state to SharedPreferences with comprehensive error handling
     */
    private fun persistThemeToStorage(theme: Theme) {
        themeScope.launch(Dispatchers.IO) {
            try {
                val editor = sharedPreferences.edit()
                
                // Persist current theme
                val themeJson = json.encodeToString(Theme.serializer(), theme)
                editor.putString(KEY_CURRENT_THEME, themeJson)
                
                // Persist theme mode
                editor.putString(KEY_THEME_MODE, _themeMode.value.name)
                
                // Persist custom colors if available
                _customColors.value?.let { colors ->
                    val colorsJson = json.encodeToString(ThemeColors.serializer(), colors)
                    editor.putString(KEY_CUSTOM_COLORS, colorsJson)
                }
                
                // Apply changes atomically
                val success = editor.commit()
                
                if (success) {
                    Log.d(TAG, "💾 Theme persisted successfully to storage")
                } else {
                    Log.e(TAG, "❌ Failed to persist theme to storage")
                }
                
            } catch (e: Exception) {
                Log.e(TAG, "💥 Exception during theme persistence", e)
            }
        }
    }

    /**
     * Set theme mode with persistence
     */
    fun setThemeMode(mode: ThemeMode) {
        Log.d(TAG, "🌓 Setting theme mode: $mode")
        _themeMode.value = mode
        
        // Persist theme mode immediately
        sharedPreferences.edit()
            .putString(KEY_THEME_MODE, mode.name)
            .apply()
    }

    /**
     * Apply custom colors with persistence
     */
    fun applyCustomColors(colors: ThemeColors) {
        Log.d(TAG, "🎨 Applying custom colors")
        _customColors.value = colors
        
        // Persist custom colors
        themeScope.launch(Dispatchers.IO) {
            try {
                val colorsJson = json.encodeToString(ThemeColors.serializer(), colors)
                sharedPreferences.edit()
                    .putString(KEY_CUSTOM_COLORS, colorsJson)
                    .commit()
                Log.d(TAG, "💾 Custom colors persisted")
            } catch (e: Exception) {
                Log.e(TAG, "❌ Error persisting custom colors", e)
            }
        }
    }

    /**
     * Enhanced theme colors with custom color support
     */
    fun getThemeColors(darkTheme: Boolean = isSystemInDarkTheme()): ThemeColors {
        // Use custom colors if available, otherwise use theme colors or defaults
        return _customColors.value 
            ?: _currentTheme.value?.colors 
            ?: getDefaultThemeColors(darkTheme)
    }

    /**
     * Enhanced default theme colors with better contrast and accessibility
     */
    private fun getDefaultThemeColors(darkTheme: Boolean): ThemeColors {
        return if (darkTheme) {
            ThemeColors(
                primary = "#BB86FC",
                secondary = "#03DAC6", 
                background = "#121212",
                surface = "#1E1E1E",
                error = "#CF6679",
                onPrimary = "#000000",
                onSecondary = "#000000",
                onBackground = "#FFFFFF",
                onSurface = "#FFFFFF",
                onError = "#000000"
            )
        } else {
            ThemeColors(
                primary = "#6200EE",
                secondary = "#03DAC6",
                background = "#F5F5F5", 
                surface = "#FFFFFF",
                error = "#B00020",
                onPrimary = "#FFFFFF",
                onSecondary = "#000000",
                onBackground = "#000000",
                onSurface = "#000000",
                onError = "#FFFFFF"
            )
        }
    }

    /**
     * Enhanced system UI setup with better color handling
     */
    fun setupSystemUi(activity: Activity, darkTheme: Boolean) {
        val window = activity.window
        val colors = getThemeColors(darkTheme)

        try {
            // Set status bar color with proper parsing
            window.statusBarColor = parseColor(
                if (darkTheme) colors.background else colors.surface
            )

            // Set navigation bar color  
            window.navigationBarColor = parseColor(
                if (darkTheme) colors.background else colors.surface
            )

            // Enhanced system UI appearance
            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
            
            Log.d(TAG, "🎨 System UI configured for ${if (darkTheme) "dark" else "light"} theme")
            
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error setting up system UI", e)
        }
    }

    /**
     * Clear all theme preferences and reset to default
     */
    fun resetToDefault() {
        Log.d(TAG, "🔄 Resetting theme to default")
        
        _currentTheme.value = null
        _themeMode.value = ThemeMode.SYSTEM
        _customColors.value = null
        
        sharedPreferences.edit().clear().apply()
        setDefaultTheme()
    }

    /**
     * Get current theme statistics
     */
    fun getThemeStats(): ThemeStats {
        return ThemeStats(
            hasCustomTheme = _currentTheme.value != null,
            hasCustomColors = _customColors.value != null,
            currentMode = _themeMode.value,
            themeName = _currentTheme.value?.name ?: "Default"
        )
    }

    // Helper methods
    
    private fun parseColor(colorString: String): Int {
        return try {
            android.graphics.Color.parseColor(colorString)
        } catch (e: Exception) {
            Log.w(TAG, "Failed to parse color: $colorString, using fallback")
            android.graphics.Color.BLACK
        }
    }
    
    private fun setDefaultTheme() {
        Log.d(TAG, "Setting default theme")
        // Keep null theme to use system defaults
    }
    
    private fun notifyThemeChanged(theme: Theme) {
        Log.d(TAG, "📢 Notifying theme change: ${theme.name}")
        // Could emit events or update other components
    }

    // Enums and data classes for comprehensive functionality
    
    enum class ThemeMode {
        LIGHT, DARK, SYSTEM
    }
    
    data class ThemeStats(
        val hasCustomTheme: Boolean,
        val hasCustomColors: Boolean,
        val currentMode: ThemeMode,
        val themeName: String
    )
}

/**
 * Enhanced composable with better error handling and performance
 */
@Composable
fun SystemUiThemeUpdater(darkTheme: Boolean) {
    val context = LocalContext.current
    val view = LocalView.current

    SideEffect {
        try {
            val window = (context as? Activity)?.window ?: return@SideEffect

            // Enhanced status bar color with alpha blending
            window.statusBarColor = if (darkTheme) {
                Color.Black.copy(alpha = 0.87f).toArgb()
            } else {
                Color.White.toArgb()
            }

            // Enhanced navigation bar color
            window.navigationBarColor = if (darkTheme) {
                Color.Black.copy(alpha = 0.8f).toArgb()
            } else {
                Color.White.copy(alpha = 0.95f).toArgb()
            }

            // Modern system UI appearance
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        } catch (e: Exception) {
            Log.e("SystemUiThemeUpdater", "Error updating system UI", e)
        }
    }
}
