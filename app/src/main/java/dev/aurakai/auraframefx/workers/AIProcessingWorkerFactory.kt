package dev.aurakai.auraframefx.workers

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

/**
 * Custom WorkerFactory for creating AI processing workers with dependency injection.
 * Handles creation of different types of AI workers based on their class name.
 */
class AIProcessingWorkerFactory @Inject constructor(
    private val workerFactories: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>
) : WorkerFactory() {
    
    private val TAG = "AIProcessingWorkerFactory"

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return try {
            val foundEntry = workerFactories.entries.find { 
                Class.forName(workerClassName).isAssignableFrom(it.key)
            }
            
            val factory = foundEntry?.value
                ?: throw IllegalArgumentException("Unknown worker class name: $workerClassName")
            
            factory.get().create(appContext, workerParameters)
        } catch (e: Exception) {
            Log.e(TAG, "Error creating worker: $workerClassName", e)
            null
        }
    }

    /**
     * Interface for worker factories that can create specific types of workers.
     */
    interface ChildWorkerFactory {
        fun create(appContext: Context, params: WorkerParameters): ListenableWorker
    }
}

/**
 * Annotation for mapping worker classes to their factories.
 */
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class WorkerKey(val value: KClass<out ListenableWorker>)

/**
 * Dagger module for providing worker bindings.
 */
@Module
@Suppress("unused")
interface WorkerBindingModule {
    @Binds
    @IntoMap
    @WorkerKey(AIProcessingWorker::class)
    fun bindAIProcessingWorker(factory: AIProcessingWorker.Factory): AIProcessingWorkerFactory.ChildWorkerFactory
    
    // Add more worker bindings here as needed
}
