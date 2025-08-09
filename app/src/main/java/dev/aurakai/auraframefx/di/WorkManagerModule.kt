package dev.aurakai.auraframefx.di

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.aurakai.auraframefx.BuildConfig
import dev.aurakai.auraframefx.workers.AIProcessingWorkerFactory
import javax.inject.Singleton

/**
 * Hilt module for WorkManager integration.
 * Configures and provides WorkManager with custom settings for the AI companion app.
 */
@Module
@InstallIn(SingletonComponent::class)
object WorkManagerModule {
    private const val TAG = "WorkManagerModule"

    /**
     * Provides WorkManager Configuration with custom settings.
     * 
     * @param workerFactory HiltWorkerFactory for dependency injection in workers
     * @param aiWorkerFactory Custom worker factory for AI processing tasks
     * @return Configured WorkManager Configuration instance
     */
    @Provides
    @Singleton
    fun provideWorkManagerConfiguration(
        workerFactory: HiltWorkerFactory,
        aiWorkerFactory: AIProcessingWorkerFactory
    ): Configuration {
        return try {
            Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .setMinimumLoggingLevel(if (BuildConfig.DEBUG) Log.DEBUG else Log.ERROR)
                .setJobSchedulerJobIdRange(1000, 2000) // Reserve job ID range
                .build()
        } catch (e: Exception) {
            Log.e(TAG, "Error creating WorkManager config", e)
            // Fallback to default configuration
            Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build()
        }
    }

    /**
     * Provides the WorkManager instance with proper initialization.
     * 
     * @param context Application context
     * @param configuration WorkManager Configuration
     * @return Configured WorkManager instance
     */
    @Provides
    @Singleton
    fun provideWorkManager(
        @ApplicationContext context: Context,
        configuration: Configuration
    ): WorkManager {
        return try {
            // Hilt handles the initialization with the provided Configuration
            WorkManager.initialize(context, configuration)
            WorkManager.getInstance(context)
        } catch (e: IllegalStateException) {
            // Handle case where WorkManager is already initialized
            Log.w(TAG, "WorkManager already initialized, using existing instance")
            WorkManager.getInstance(context)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize WorkManager", e)
            // Provide fallback instance
            WorkManager.getInstance(context)
        }
    }

    /**
     * Provides a custom worker factory for AI processing tasks.
     * 
     * @param context Application context
     * @return Configured AIProcessingWorkerFactory
     */
    @Provides
    @Singleton
    fun provideAIWorkerFactory(
        @ApplicationContext context: Context
    ): AIProcessingWorkerFactory {
        return AIProcessingWorkerFactory(context)
    }
}
