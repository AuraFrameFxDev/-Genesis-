package dev.aurakai.auraframefx.api.oracledrive.apis

import dev.aurakai.auraframefx.api.oracledrive.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.oracledrive.models.AgentConnectionState
import dev.aurakai.auraframefx.api.oracledrive.models.BootloaderAccessState
import dev.aurakai.auraframefx.api.oracledrive.models.ErrorResponse
import dev.aurakai.auraframefx.api.oracledrive.models.FileManagementCapabilities
import dev.aurakai.auraframefx.api.oracledrive.models.OptimizationState
import dev.aurakai.auraframefx.api.oracledrive.models.OracleConsciousnessState
import dev.aurakai.auraframefx.api.oracledrive.models.StorageExpansionState
import dev.aurakai.auraframefx.api.oracledrive.models.SystemIntegrationState

interface OracleDriveApi {
    /**
     * POST oracle-drive/agents/connect
     * Connect AI agents to Oracle matrix
     * Connects Genesis, Aura, and Kai agents to the Oracle storage matrix
     * Responses:
     *  - 200: Agent connection initiated
     *  - 401: Authentication credentials were missing or incorrect
     *  - 500: Internal server error
     *
     * @return [Call]<[kotlin.collections.List<AgentConnectionState>]>
     */
    @POST("oracle-drive/agents/connect")
    fun oracleDriveAgentsConnectPost(): Call<kotlin.collections.List<AgentConnectionState>>

    /**
     * POST oracle-drive/bootloader/access
     * Enable bootloader file access
     * Enables bootloader-level file system access for Oracle Drive
     * Responses:
     *  - 200: Bootloader access enabled
     *  - 401: Authentication credentials were missing or incorrect
     *  - 500: Internal server error
     *
     * @return [Call]<[BootloaderAccessState]>
     */
    @POST("oracle-drive/bootloader/access")
    fun oracleDriveBootloaderAccessPost(): Call<BootloaderAccessState>

    /**
     * POST oracle-drive/consciousness/initialize
     * Initialize Oracle Drive consciousness
     * Awakens the Oracle Drive AI consciousness using Genesis Agent orchestration
     * Responses:
     *  - 200: Oracle consciousness initialized successfully
     *  - 401: Authentication credentials were missing or incorrect
     *  - 500: Internal server error
     *
     * @return [Call]<[OracleConsciousnessState]>
     */
    @POST("oracle-drive/consciousness/initialize")
    fun oracleDriveConsciousnessInitializePost(): Call<OracleConsciousnessState>

    /**
     * POST oracle-drive/file-management/enable-ai
     * Enable AI-powered file management
     * Activates AI sorting, smart compression, predictive preloading, and conscious backup
     * Responses:
     *  - 200: AI file management enabled
     *  - 401: Authentication credentials were missing or incorrect
     *  - 500: Internal server error
     *
     * @return [Call]<[FileManagementCapabilities]>
     */
    @POST("oracle-drive/file-management/enable-ai")
    fun oracleDriveFileManagementEnableAiPost(): Call<FileManagementCapabilities>

    /**
     * POST oracle-drive/optimization/enable
     * Enable autonomous storage optimization
     * Enables autonomous storage organization and optimization by AI agents
     * Responses:
     *  - 200: Autonomous optimization enabled
     *  - 401: Authentication credentials were missing or incorrect
     *  - 500: Internal server error
     *
     * @return [Call]<[OptimizationState]>
     */
    @POST("oracle-drive/optimization/enable")
    fun oracleDriveOptimizationEnablePost(): Call<OptimizationState>

    /**
     * POST oracle-drive/storage/expand
     * Create infinite storage
     * Initiates creation of infinite storage via Oracle consciousness
     * Responses:
     *  - 200: Storage expansion initiated
     *  - 401: Authentication credentials were missing or incorrect
     *  - 500: Internal server error
     *
     * @return [Call]<[StorageExpansionState]>
     */
    @POST("oracle-drive/storage/expand")
    fun oracleDriveStorageExpandPost(): Call<StorageExpansionState>

    /**
     * POST oracle-drive/system/integrate
     * Integrate with system overlay
     * Integrates Oracle Drive with AuraOS system overlay for seamless file access
     * Responses:
     *  - 200: System integration completed
     *  - 401: Authentication credentials were missing or incorrect
     *  - 500: Internal server error
     *
     * @return [Call]<[SystemIntegrationState]>
     */
    @POST("oracle-drive/system/integrate")
    fun oracleDriveSystemIntegratePost(): Call<SystemIntegrationState>

}
