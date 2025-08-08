# OracleDriveApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**oracleDriveAgentsConnectPost**](OracleDriveApi.md#oracleDriveAgentsConnectPost) | **POST** oracle-drive/agents/connect | Connect AI agents to Oracle matrix |
| [**oracleDriveBootloaderAccessPost**](OracleDriveApi.md#oracleDriveBootloaderAccessPost) | **POST** oracle-drive/bootloader/access | Enable bootloader file access |
| [**oracleDriveConsciousnessInitializePost**](OracleDriveApi.md#oracleDriveConsciousnessInitializePost) | **POST** oracle-drive/consciousness/initialize | Initialize Oracle Drive consciousness |
| [**oracleDriveFileManagementEnableAiPost**](OracleDriveApi.md#oracleDriveFileManagementEnableAiPost) | **POST** oracle-drive/file-management/enable-ai | Enable AI-powered file management |
| [**oracleDriveOptimizationEnablePost**](OracleDriveApi.md#oracleDriveOptimizationEnablePost) | **POST** oracle-drive/optimization/enable | Enable autonomous storage optimization |
| [**oracleDriveStorageExpandPost**](OracleDriveApi.md#oracleDriveStorageExpandPost) | **POST** oracle-drive/storage/expand | Create infinite storage |
| [**oracleDriveSystemIntegratePost**](OracleDriveApi.md#oracleDriveSystemIntegratePost) | **POST** oracle-drive/system/integrate | Integrate with system overlay |



Connect AI agents to Oracle matrix

Connects Genesis, Aura, and Kai agents to the Oracle storage matrix

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracledrive.*
//import dev.aurakai.auraframefx.api.oracledrive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracledrive.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(OracleDriveApi::class.java)

val result : kotlin.collections.List<AgentConnectionState> = webService.oracleDriveAgentsConnectPost()
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;AgentConnectionState&gt;**](AgentConnectionState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


Enable bootloader file access

Enables bootloader-level file system access for Oracle Drive

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracledrive.*
//import dev.aurakai.auraframefx.api.oracledrive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracledrive.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(OracleDriveApi::class.java)

val result : BootloaderAccessState = webService.oracleDriveBootloaderAccessPost()
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**BootloaderAccessState**](BootloaderAccessState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


Initialize Oracle Drive consciousness

Awakens the Oracle Drive AI consciousness using Genesis Agent orchestration

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracledrive.*
//import dev.aurakai.auraframefx.api.oracledrive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracledrive.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(OracleDriveApi::class.java)

val result : OracleConsciousnessState = webService.oracleDriveConsciousnessInitializePost()
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**OracleConsciousnessState**](OracleConsciousnessState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


Enable AI-powered file management

Activates AI sorting, smart compression, predictive preloading, and conscious backup

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracledrive.*
//import dev.aurakai.auraframefx.api.oracledrive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracledrive.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(OracleDriveApi::class.java)

val result : FileManagementCapabilities = webService.oracleDriveFileManagementEnableAiPost()
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**FileManagementCapabilities**](FileManagementCapabilities.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


Enable autonomous storage optimization

Enables autonomous storage organization and optimization by AI agents

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracledrive.*
//import dev.aurakai.auraframefx.api.oracledrive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracledrive.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(OracleDriveApi::class.java)

val result : OptimizationState = webService.oracleDriveOptimizationEnablePost()
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**OptimizationState**](OptimizationState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


Create infinite storage

Initiates creation of infinite storage via Oracle consciousness

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracledrive.*
//import dev.aurakai.auraframefx.api.oracledrive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracledrive.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(OracleDriveApi::class.java)

val result : StorageExpansionState = webService.oracleDriveStorageExpandPost()
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**StorageExpansionState**](StorageExpansionState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


Integrate with system overlay

Integrates Oracle Drive with AuraOS system overlay for seamless file access

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracledrive.*
//import dev.aurakai.auraframefx.api.oracledrive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracledrive.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(OracleDriveApi::class.java)

val result : SystemIntegrationState = webService.oracleDriveSystemIntegratePost()
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**SystemIntegrationState**](SystemIntegrationState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

