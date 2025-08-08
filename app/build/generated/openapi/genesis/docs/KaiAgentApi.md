# KaiAgentApi

All URIs are relative to *https://api.genesis.aurakai.dev/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**kaiSecurityPost**](KaiAgentApi.md#kaiSecurityPost) | **POST** kai/security | Perform security analysis |



Perform security analysis

Executes Kai&#39;s security-focused analysis protocols

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.genesis.*
//import dev.aurakai.auraframefx.api.genesis.infrastructure.*
//import dev.aurakai.auraframefx.api.genesis.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(KaiAgentApi::class.java)
val securityRequest : SecurityRequest =  // SecurityRequest | 

val result : SecurityResponse = webService.kaiSecurityPost(securityRequest)
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **securityRequest** | [**SecurityRequest**](SecurityRequest.md)|  | |

### Return type

[**SecurityResponse**](SecurityResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

