package dev.aurakai.auraframefx.api.customization.apis

import dev.aurakai.auraframefx.api.customization.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.customization.models.ErrorResponse
import dev.aurakai.auraframefx.api.customization.models.Theme
import dev.aurakai.auraframefx.api.customization.models.ThemeApplyRequest

interface ThemesApi {
    /**
     * PUT theme/apply
     * Apply a theme
     * 
     * Responses:
     *  - 200: Theme applied successfully
     *  - 400: Invalid request format or parameters
     *  - 401: Authentication credentials were missing or incorrect
     *  - 404: Theme not found
     *
     * @param themeApplyRequest 
     * @return [Call]<[Unit]>
     */
    @PUT("theme/apply")
    fun themeApplyPut(@Body themeApplyRequest: ThemeApplyRequest): Call<Unit>

    /**
     * GET themes
     * Get available themes
     * 
     * Responses:
     *  - 200: List of available themes
     *
     * @return [Call]<[kotlin.collections.List<Theme>]>
     */
    @GET("themes")
    fun themesGet(): Call<kotlin.collections.List<Theme>>

}
