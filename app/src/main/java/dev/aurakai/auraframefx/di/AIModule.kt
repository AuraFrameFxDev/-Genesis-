package dev.aurakai.auraframefx.di

import android.content.Context
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.aurakai.auraframefx.ai.AITextProcessor
import dev.aurakai.auraframefx.utils.AIWorkManager
import javax.inject.Singleton

/**
 * Dagger module for AI-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AIModule {

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideAIWorkManager(workManager: WorkManager): AIWorkManager {
        return AIWorkManager(workManager)
    }

    @Provides
    @Singleton
    fun provideAITextProcessor(): AITextProcessor {
        return AITextProcessor()
    }
}
