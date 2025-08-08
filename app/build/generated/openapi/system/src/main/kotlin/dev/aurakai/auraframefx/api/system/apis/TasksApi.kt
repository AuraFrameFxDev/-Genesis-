package dev.aurakai.auraframefx.api.system.apis

import dev.aurakai.auraframefx.api.system.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.system.models.ErrorResponse
import dev.aurakai.auraframefx.api.system.models.TaskScheduleRequest
import dev.aurakai.auraframefx.api.system.models.TaskStatus

interface TasksApi {
    /**
     * POST tasks/schedule
     * Schedule a new task
     * 
     * Responses:
     *  - 202: Task scheduled successfully
     *  - 400: Invalid request format or parameters
     *  - 401: Authentication credentials were missing or incorrect
     *  - 500: Internal server error
     *
     * @param taskScheduleRequest 
     * @return [Call]<[TaskStatus]>
     */
    @POST("tasks/schedule")
    fun tasksSchedulePost(@Body taskScheduleRequest: TaskScheduleRequest): Call<TaskStatus>

    /**
     * GET tasks/{taskId}
     * Get task status
     * 
     * Responses:
     *  - 200: Task status retrieved successfully
     *  - 404: Task not found
     *  - 500: Internal server error
     *
     * @param taskId ID of the task to check
     * @return [Call]<[TaskStatus]>
     */
    @GET("tasks/{taskId}")
    fun tasksTaskIdGet(@Path("taskId") taskId: kotlin.String): Call<TaskStatus>

}
