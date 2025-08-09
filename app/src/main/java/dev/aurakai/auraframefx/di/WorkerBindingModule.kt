package dev.aurakai.auraframefx.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dev.aurakai.auraframefx.workers.AIProcessingWorkerFactory
import dev.aurakai.auraframefx.workers.AITextProcessingWorker
import dev.aurakai.auraframefx.workers.WorkerKey
import javax.inject.Singleton

/**
 * Dagger module for binding worker factories.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class WorkerBindingModule {
    
    @Binds
    @IntoMap
    @WorkerKey(AITextProcessingWorker::class)
    abstract fun bindAITextProcessingWorker(
        factory: AITextProcessingWorker.Factory
    ): AIProcessingWorkerFactory.ChildWorkerFactory
    
    // Add more worker bindings here as needed
}

/**
 * Module for providing worker-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {
    
    @Provides
    @Singleton
    fun provideAIWorkerFactory(
        workerFactories: Map<Class<out AIProcessingWorkerFactory.ChildWorkerFactory>, @JvmSuppressWildcards javax.inject.Provider<AIProcessingWorkerFactory.ChildWorkerFactory>>
    ): AIProcessingWorkerFactory {
        return AIProcessingWorkerFactory(workerFactories)
    }
}
