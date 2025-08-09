package dev.aurakai.auraframefx.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.aurakai.auraframefx.auth.OAuthService
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides authentication-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    
    /**
     * Provides the Google Sign-In options with the required configuration.
     * 
     * @param context The application context
     * @return Configured [GoogleSignInOptions]
     */
    @Provides
    @Singleton
    fun provideGoogleSignInOptions(
        @ApplicationContext context: Context
    ): GoogleSignInOptions {
        // In a real app, you should use a proper configuration management system
        // and not hardcode the server client ID
        val serverClientId = context.getString(
            context.resources.getIdentifier(
                "default_web_client_id",
                "string",
                context.packageName
            )
        )
        
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(serverClientId)
            .requestEmail()
            .requestProfile()
            .build()
    }
    
    /**
     * Provides the Google Sign-In client.
     * 
     * @param context The application context
     * @param gso The Google Sign-In options
     * @return Configured [GoogleSignInClient]
     */
    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        @ApplicationContext context: Context,
        gso: GoogleSignInOptions
    ): GoogleSignInClient {
        return GoogleSignIn.getClient(context, gso)
    }
    
    /**
     * Provides the OAuth service for handling authentication flows.
     * 
     * @param context The application context
     * @param googleSignInClient The Google Sign-In client
     * @return Configured [OAuthService] instance
     */
    @Provides
    @Singleton
    fun provideOAuthService(
        @ApplicationContext context: Context,
        googleSignInClient: GoogleSignInClient
    ): OAuthService {
        return OAuthService(context, googleSignInClient)
    }
}
