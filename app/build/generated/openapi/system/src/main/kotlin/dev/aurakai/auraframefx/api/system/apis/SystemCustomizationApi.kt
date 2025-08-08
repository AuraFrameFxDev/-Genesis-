package dev.aurakai.auraframefx.api.system.apis

import dev.aurakai.auraframefx.api.system.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.system.models.ErrorResponse
import dev.aurakai.auraframefx.api.system.models.LockScreenConfig

interface SystemCustomizationApi {
    /**
     * GET system/lockscreen-config
     * Get lock screen configuration
     * 
     * Responses:
     *  - 200: Lock screen configuration retrieved successfully
     *  - 401: Authentication credentials were missing or incorrect
     *
     * @return [Call]<[LockScreenConfig]>
     */
    @GET("system/lockscreen-config")
    fun systemLockscreenConfigGet(): Call<LockScreenConfig>

    /**
     * PUT system/lockscreen-config
     * Update lock screen configuration
     * 
     * Responses:
     *  - 200: Lock screen configuration updated successfully
     *  - 400: Invalid request format or parameters
     *  - 401: Authentication credentials were missing or incorrect
     *
     * @param lockScreenConfig 
     * @return [Call]<[Unit]>
     */
    @PUT("system/lockscreen-config")
    fun systemLockscreenConfigPut(@Body lockScreenConfig: LockScreenConfig): Call<Unit>

}
