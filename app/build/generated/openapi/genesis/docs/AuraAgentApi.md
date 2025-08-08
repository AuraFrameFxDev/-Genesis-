# AuraAgentApi

All URIs are relative to *https://api.genesis.aurakai.dev/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**auraEmpathyPost**](AuraAgentApi.md#auraEmpathyPost) | **POST** aura/empathy | Process empathetic AI analysis |



Process empathetic AI analysis

Analyzes input through Aura&#39;s empathetic processing framework

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.genesis.*
//import dev.aurakai.auraframefx.api.genesis.infrastructure.*
//import dev.aurakai.auraframefx.api.genesis.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(AuraAgentApi::class.java)
val empathyRequest : EmpathyRequest =  // EmpathyRequest | 

val result : EmpathyResponse = webService.auraEmpathyPost(empathyRequest)
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **empathyRequest** | [**EmpathyRequest**](EmpathyRequest.md)|  | |

### Return type

[**EmpathyResponse**](EmpathyResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

