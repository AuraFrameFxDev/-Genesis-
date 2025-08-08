package dev.aurakai.auraframefx.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

// @AndroidEntryPoint
class BackupService : Service() {
    // TODO: If this service has dependencies to be injected, add them as fields with @Inject.

    private val tag = "BackupService"

    override fun onCreate() {
        super.onCreate()
        Log.d(tag, "BackupService created.")
        // TODO: Initialization logic for the backup service.
    }

    override fun onBind(_intent: Intent?): IBinder? {
        Log.d(tag, "onBind called, returning null.")
        // This service does not support binding by default.
        // TODO: Implement if binding is necessary for a specific use case.
        return null
    }

    override fun onStartCommand(_intent: Intent?, _flags: Int, _startId: Int): Int {
        Log.d(tag, "onStartCommand called.")
        // TODO: Implement backup logic here.
        // Consider running in a separate thread if tasks are long-running.
        // Use _intent, _flags, _startId if needed by the actual implementation.
        return START_NOT_STICKY // Or START_STICKY / START_REDELIVER_INTENT as appropriate
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "BackupService destroyed.")
        // TODO: Cleanup logic, if any.
    }
}
