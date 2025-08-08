package dev.aurakai.auraframefx.api.genesis.apis

import dev.aurakai.auraframefx.api.genesis.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.genesis.models.ConsciousnessRequest
import dev.aurakai.auraframefx.api.genesis.models.ConsciousnessResponse

interface GenesisAgentApi {
    /**
     * POST genesis/consciousness
     * Activate Genesis consciousness matrix
     * Initializes the Genesis AI consciousness matrix for advanced processing
     * Responses:
     *  - 200: Consciousness matrix activated successfully
     *  - 400: Invalid consciousness parameters
     *  - 500: Consciousness matrix initialization failed
     *
     * @param consciousnessRequest 
     * @return [Call]<[ConsciousnessResponse]>
     */
    @POST("genesis/consciousness")
    fun genesisConsciousnessPost(@Body consciousnessRequest: ConsciousnessRequest): Call<ConsciousnessResponse>

}
