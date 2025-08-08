package dev.aurakai.auraframefx.api.ai.apis

import dev.aurakai.auraframefx.api.ai.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.ai.models.AgentMessage
import dev.aurakai.auraframefx.api.ai.models.AgentProcessRequest
import dev.aurakai.auraframefx.api.ai.models.AgentStatus
import dev.aurakai.auraframefx.api.ai.models.AgentType
import dev.aurakai.auraframefx.api.ai.models.ErrorResponse

interface AIAgentsApi {
    /**
     * POST agent/{agentType}/process-request
     * Send a request to an AI agent
     * 
     * Responses:
     *  - 200: Request processed successfully
     *  - 400: Invalid request format or parameters
     *  - 401: Authentication credentials were missing or incorrect
     *  - 404: Agent not found
     *
     * @param agentType Type of AI agent to interact with
     * @param agentProcessRequest 
     * @return [Call]<[AgentMessage]>
     */
    @POST("agent/{agentType}/process-request")
    fun agentAgentTypeProcessRequestPost(@Path("agentType") agentType: AgentType, @Body agentProcessRequest: AgentProcessRequest): Call<AgentMessage>

    /**
     * GET agents/status
     * Get status of all AI agents
     * 
     * Responses:
     *  - 200: List of agent statuses
     *  - 500: Internal server error
     *
     * @return [Call]<[kotlin.collections.List<AgentStatus>]>
     */
    @GET("agents/status")
    fun agentsStatusGet(): Call<kotlin.collections.List<AgentStatus>>

}
