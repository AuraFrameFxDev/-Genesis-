package dev.aurakai.auraframefx.api.sandbox.apis

import dev.aurakai.auraframefx.api.sandbox.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.sandbox.models.ComponentTestRequest
import dev.aurakai.auraframefx.api.sandbox.models.ComponentTestResult
import dev.aurakai.auraframefx.api.sandbox.models.ErrorResponse
import dev.aurakai.auraframefx.api.sandbox.models.SandboxComponent

interface SandboxApi {
    /**
     * POST sandbox/components/{componentId}/test
     * Execute component test
     * Run tests on a specific UI component in the sandbox environment
     * Responses:
     *  - 200: Component test executed successfully
     *  - 400: Bad request
     *  - 401: Unauthorized
     *  - 404: Component not found
     *  - 500: Internal server error
     *
     * @param componentId ID of the component to test
     * @param componentTestRequest 
     * @return [Call]<[ComponentTestResult]>
     */
    @POST("sandbox/components/{componentId}/test")
    fun sandboxComponentsComponentIdTestPost(@Path("componentId") componentId: kotlin.String, @Body componentTestRequest: ComponentTestRequest): Call<ComponentTestResult>

    /**
     * GET sandbox/components
     * List available UI components for testing
     * Retrieve list of UI components available in the sandbox environment
     * Responses:
     *  - 200: Component list retrieved successfully
     *  - 401: Unauthorized
     *  - 500: Internal server error
     *
     * @return [Call]<[kotlin.collections.List<SandboxComponent>]>
     */
    @GET("sandbox/components")
    fun sandboxComponentsGet(): Call<kotlin.collections.List<SandboxComponent>>

}
