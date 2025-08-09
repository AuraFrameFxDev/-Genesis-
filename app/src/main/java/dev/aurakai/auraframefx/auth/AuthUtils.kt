package dev.aurakai.auraframefx.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException

/**
 * Extension function to register for Google Sign-In result in an Activity.
 *
 * @param onSuccess Callback invoked when sign-in is successful
 * @param onFailure Callback invoked when sign-in fails
 * @return A launcher that can be used to start the sign-in flow
 */
fun AppCompatActivity.registerForGoogleSignInResult(
    onSuccess: (GoogleSignInAccount) -> Unit,
    onFailure: (Exception) -> Unit = {}
) = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
        val intent = result.data
        try {
            val task = com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(intent)
            val account = task.getResult(ApiException::class.java)
            onSuccess(account)
        } catch (e: ApiException) {
            Log.e("AuthUtils", "Google sign in failed", e)
            onFailure(e)
        }
    } else {
        onFailure(RuntimeException("Sign in was canceled or failed"))
    }
}

/**
 * Extension function to register for Google Sign-In result in a Fragment.
 *
 * @param onSuccess Callback invoked when sign-in is successful
 * @param onFailure Callback invoked when sign-in fails
 * @return A launcher that can be used to start the sign-in flow
 */
fun Fragment.registerForGoogleSignInResult(
    onSuccess: (GoogleSignInAccount) -> Unit,
    onFailure: (Exception) -> Unit = {}
) = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
        val intent = result.data
        try {
            val task = com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(intent)
            val account = task.getResult(ApiException::class.java)
            onSuccess(account)
        } catch (e: ApiException) {
            Log.e("AuthUtils", "Google sign in failed", e)
            onFailure(e)
        }
    } else {
        onFailure(RuntimeException("Sign in was canceled or failed"))
    }
}

/**
 * Extension function to check if a user is signed in.
 *
 * @return `true` if a user is signed in, `false` otherwise
 */
fun Context.isUserSignedIn(): Boolean {
    val account = com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount(this)
    return account != null
}

/**
 * Extension function to get the current user's display name.
 *
 * @return The user's display name, or null if not signed in
 */
fun Context.getCurrentUserDisplayName(): String? {
    return com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount(this)?.displayName
}

/**
 * Extension function to get the current user's email.
 *
 * @return The user's email, or null if not signed in
 */
fun Context.getCurrentUserEmail(): String? {
    return com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount(this)?.email
}

/**
 * Extension function to get the current user's profile photo URL.
 *
 * @return The URL of the user's profile photo, or null if not available
 */
fun Context.getCurrentUserPhotoUrl(): String? {
    return com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount(this)?.photoUrl?.toString()
}

/**
 * Extension function to get the current user's ID token.
 *
 * @return The user's ID token, or null if not available
 */
fun Context.getCurrentUserIdToken(): String? {
    return com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount(this)?.idToken
}
