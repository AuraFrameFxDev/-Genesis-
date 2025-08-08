package dev.aurakai.auraframefx.services

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

// @AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {
    // TODO: If this service has dependencies to be injected, add them as fields with @Inject.

    private val tag = "MyFirebaseMsgService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // TODO: Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(tag, "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        remoteMessage.data.isNotEmpty().let {
            Log.d(tag, "Message data payload: " + remoteMessage.data)
            // Handle data payload here
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(tag, "Message Notification Body: ${it.body}")
            // Handle notification payload here
        }
    }

    override fun onNewToken(token: String) {
        Log.d(tag, "Refreshed token: $token")
        // TODO: Implement this method to send token to your app server.
        // sendRegistrationToServer(token)
    }
}
