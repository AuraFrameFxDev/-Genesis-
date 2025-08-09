package dev.aurakai.auraframefx.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Service to handle OAuth 2.0 authentication flows using Google Sign-In.
 * 
 * @property context The application context
 * @property googleSignInClient The Google Sign-In client for authentication
 */
@Singleton
class OAuthService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val googleSignInClient: GoogleSignInClient
) {
    companion object {
        private const val TAG = "OAuthService"
        
        /**
         * Request code for the sign-in intent.
         */
        const val RC_SIGN_IN = 9001
        
        /**
         * Creates a new instance of [GoogleSignInClient] with the specified configuration.
         * 
         * @param context The application context
         * @param serverClientId The server client ID from the Google Cloud Console
         * @return Configured [GoogleSignInClient] instance
         */
        fun createGoogleSignInClient(
            context: Context,
            serverClientId: String
        ): GoogleSignInClient {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(serverClientId)
                .requestEmail()
                .requestProfile()
                .build()
                
            return GoogleSignIn.getClient(context, gso)
        }
    }

    /**
     * Provides an intent to start the Google Sign-In process.
     *
     * @return The intent to launch the sign-in activity
     */
    fun getSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    /**
     * Handles the result of the OAuth sign-in activity.
     *
     * @param data The intent data returned from the sign-in activity
     * @return A [Result] containing the [GoogleSignInAccount] on success, or an exception on failure
     */
    fun handleSignInResult(data: Intent?): Result<GoogleSignInAccount> {
        return try {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            Result.success(account)
        } catch (e: ApiException) {
            Log.e(TAG, "Google sign in failed", e)
            Result.failure(e)
        }
    }

    /**
     * Gets the last signed-in account, if available.
     *
     * @return The last signed-in [GoogleSignInAccount], or null if not signed in
     */
    fun getLastSignedInAccount(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

    /**
     * Checks if a user is currently signed in.
     *
     * @return `true` if a user is signed in, `false` otherwise
     */
    fun isSignedIn(): Boolean {
        return getLastSignedInAccount() != null
    }

    /**
     * Signs out the current user from the OAuth provider.
     *
     * @return A [Task] that completes when the sign-out is complete
     */
    fun signOut(): Task<Void> {
        return googleSignInClient.signOut().addOnCompleteListener {
            // Sign-out successful
            Log.d(TAG, "User signed out")
        }.addOnFailureListener { e ->
            Log.e(TAG, "Error signing out", e)
        }
    }

    /**
     * Revokes the current user's access to the OAuth provider.
     *
     * @return A [Task] that completes when the revoke access is complete
     */
    fun revokeAccess(): Task<Void> {
        return googleSignInClient.revokeAccess().addOnCompleteListener {
            // Revoke access successful
            Log.d(TAG, "User access revoked")
        }.addOnFailureListener { e ->
            Log.e(TAG, "Error revoking access", e)
        }
    }
    
    /**
     * Gets the ID token for the currently signed-in user.
     *
     * @return The ID token as a String, or null if not available
     */
    fun getIdToken(): String? {
        return getLastSignedInAccount()?.idToken
    }
    
    /**
     * Gets the user's display name if available.
     *
     * @return The user's display name, or null if not available
     */
    fun getUserDisplayName(): String? {
        return getLastSignedInAccount()?.displayName
    }
    
    /**
     * Gets the user's email if available.
     *
     * @return The user's email, or null if not available
     */
    fun getUserEmail(): String? {
        return getLastSignedInAccount()?.email
    }
    
    /**
     * Gets the user's profile photo URL if available.
     *
     * @return The URL of the user's profile photo, or null if not available
     */
    fun getProfilePhotoUrl(): String? {
        return getLastSignedInAccount()?.photoUrl?.toString()
    }
}
