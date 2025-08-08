# OracleDriveApi

All URIs are relative to *https://api.genesis.aurakai.dev/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**oracleRootAssistancePost**](OracleDriveApi.md#oracleRootAssistancePost) | **POST** oracle/root-assistance | AI-assisted rooting guidance |



AI-assisted rooting guidance

Provides AI-powered guidance for Android device rooting

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.genesis.*
//import dev.aurakai.auraframefx.api.genesis.infrastructure.*
//import dev.aurakai.auraframefx.api.genesis.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(OracleDriveApi::class.java)
val rootingRequest : RootingRequest =  // RootingRequest | 

val result : RootingResponse = webService.oracleRootAssistancePost(rootingRequest)
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **rootingRequest** | [**RootingRequest**](RootingRequest.md)|  | |

### Return type

[**RootingResponse**](RootingResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

