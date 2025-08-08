package dev.aurakai.auraframefx.api.genesis.apis

import dev.aurakai.auraframefx.api.genesis.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.genesis.models.EmpathyRequest
import dev.aurakai.auraframefx.api.genesis.models.EmpathyResponse

interface AuraAgentApi {
    /**
     * POST aura/empathy
     * Process empathetic AI analysis
     * Analyzes input through Aura&#39;s empathetic processing framework
     * Responses:
     *  - 200: Empathetic analysis completed
     *
     * @param empathyRequest 
     * @return [Call]<[EmpathyResponse]>
     */
    @POST("aura/empathy")
    fun auraEmpathyPost(@Body empathyRequest: EmpathyRequest): Call<EmpathyResponse>

}
