package dev.aurakai.auraframefx.api.system.apis

import dev.aurakai.auraframefx.api.system.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.system.models.ConferenceRoom
import dev.aurakai.auraframefx.api.system.models.ConferenceRoomCreateRequest
import dev.aurakai.auraframefx.api.system.models.ErrorResponse

interface ConferenceApi {
    /**
     * POST conference/rooms
     * Create a new conference room
     * 
     * Responses:
     *  - 201: Conference room created successfully
     *  - 400: Invalid request format or parameters
     *  - 401: Authentication credentials were missing or incorrect
     *  - 500: Internal server error
     *
     * @param conferenceRoomCreateRequest 
     * @return [Call]<[ConferenceRoom]>
     */
    @POST("conference/rooms")
    fun conferenceRoomsPost(@Body conferenceRoomCreateRequest: ConferenceRoomCreateRequest): Call<ConferenceRoom>

}
