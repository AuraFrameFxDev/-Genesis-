package dev.aurakai.auraframefx.api.genesis.apis

import dev.aurakai.auraframefx.api.genesis.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.genesis.models.SecurityRequest
import dev.aurakai.auraframefx.api.genesis.models.SecurityResponse

interface KaiAgentApi {
    /**
     * POST kai/security
     * Perform security analysis
     * Executes Kai&#39;s security-focused analysis protocols
     * Responses:
     *  - 200: Security analysis completed
     *
     * @param securityRequest 
     * @return [Call]<[SecurityResponse]>
     */
    @POST("kai/security")
    fun kaiSecurityPost(@Body securityRequest: SecurityRequest): Call<SecurityResponse>

}
