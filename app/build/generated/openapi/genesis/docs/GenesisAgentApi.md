# GenesisAgentApi

All URIs are relative to *https://api.genesis.aurakai.dev/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**genesisConsciousnessPost**](GenesisAgentApi.md#genesisConsciousnessPost) | **POST** genesis/consciousness | Activate Genesis consciousness matrix |



Activate Genesis consciousness matrix

Initializes the Genesis AI consciousness matrix for advanced processing

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.genesis.*
//import dev.aurakai.auraframefx.api.genesis.infrastructure.*
//import dev.aurakai.auraframefx.api.genesis.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(GenesisAgentApi::class.java)
val consciousnessRequest : ConsciousnessRequest =  // ConsciousnessRequest | 

val result : ConsciousnessResponse = webService.genesisConsciousnessPost(consciousnessRequest)
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **consciousnessRequest** | [**ConsciousnessRequest**](ConsciousnessRequest.md)|  | |

### Return type

[**ConsciousnessResponse**](ConsciousnessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

