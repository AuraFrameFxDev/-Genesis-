package dev.aurakai.auraframefx.api.ai.apis

import dev.aurakai.auraframefx.api.ai.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.ai.models.ErrorResponse
import dev.aurakai.auraframefx.api.ai.models.GenerateImageDescriptionRequest
import dev.aurakai.auraframefx.api.ai.models.GenerateImageDescriptionResponse
import dev.aurakai.auraframefx.api.ai.models.GenerateTextRequest
import dev.aurakai.auraframefx.api.ai.models.GenerateTextResponse

interface AIContentApi {
    /**
     * POST ai/generate/image-description
     * Generate image description using AI
     * 
     * Responses:
     *  - 200: Image description generated successfully
     *  - 400: Invalid request format or parameters
     *  - 401: Authentication credentials were missing or incorrect
     *  - 500: Internal server error
     *
     * @param generateImageDescriptionRequest 
     * @return [Call]<[GenerateImageDescriptionResponse]>
     */
    @POST("ai/generate/image-description")
    fun aiGenerateImageDescriptionPost(@Body generateImageDescriptionRequest: GenerateImageDescriptionRequest): Call<GenerateImageDescriptionResponse>

    /**
     * POST ai/generate/text
     * Generate text using AI
     * 
     * Responses:
     *  - 200: Text generation successful
     *  - 400: Invalid request format or parameters
     *  - 401: Authentication credentials were missing or incorrect
     *  - 429: Rate limit exceeded
     *  - 500: Internal server error
     *
     * @param generateTextRequest 
     * @return [Call]<[GenerateTextResponse]>
     */
    @POST("ai/generate/text")
    fun aiGenerateTextPost(@Body generateTextRequest: GenerateTextRequest): Call<GenerateTextResponse>

}
