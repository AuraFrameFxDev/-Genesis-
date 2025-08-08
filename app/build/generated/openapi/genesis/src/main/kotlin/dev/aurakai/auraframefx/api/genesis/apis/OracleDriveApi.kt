package dev.aurakai.auraframefx.api.genesis.apis

import dev.aurakai.auraframefx.api.genesis.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.genesis.models.RootingRequest
import dev.aurakai.auraframefx.api.genesis.models.RootingResponse

interface OracleDriveApi {
    /**
     * POST oracle/root-assistance
     * AI-assisted rooting guidance
     * Provides AI-powered guidance for Android device rooting
     * Responses:
     *  - 200: Rooting guidance generated
     *
     * @param rootingRequest 
     * @return [Call]<[RootingResponse]>
     */
    @POST("oracle/root-assistance")
    fun oracleRootAssistancePost(@Body rootingRequest: RootingRequest): Call<RootingResponse>

}
