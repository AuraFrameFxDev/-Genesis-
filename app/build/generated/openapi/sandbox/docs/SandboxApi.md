# SandboxApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**sandboxComponentsComponentIdTestPost**](SandboxApi.md#sandboxComponentsComponentIdTestPost) | **POST** sandbox/components/{componentId}/test | Execute component test |
| [**sandboxComponentsGet**](SandboxApi.md#sandboxComponentsGet) | **GET** sandbox/components | List available UI components for testing |



Execute component test

Run tests on a specific UI component in the sandbox environment

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.sandbox.*
//import dev.aurakai.auraframefx.api.sandbox.infrastructure.*
//import dev.aurakai.auraframefx.api.sandbox.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(SandboxApi::class.java)
val componentId : kotlin.String = componentId_example // kotlin.String | ID of the component to test
val componentTestRequest : ComponentTestRequest =  // ComponentTestRequest | 

val result : ComponentTestResult = webService.sandboxComponentsComponentIdTestPost(componentId, componentTestRequest)
```

### Parameters
| **componentId** | **kotlin.String**| ID of the component to test | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **componentTestRequest** | [**ComponentTestRequest**](ComponentTestRequest.md)|  | |

### Return type

[**ComponentTestResult**](ComponentTestResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


List available UI components for testing

Retrieve list of UI components available in the sandbox environment

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.sandbox.*
//import dev.aurakai.auraframefx.api.sandbox.infrastructure.*
//import dev.aurakai.auraframefx.api.sandbox.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(SandboxApi::class.java)

val result : kotlin.collections.List<SandboxComponent> = webService.sandboxComponentsGet()
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;SandboxComponent&gt;**](SandboxComponent.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

