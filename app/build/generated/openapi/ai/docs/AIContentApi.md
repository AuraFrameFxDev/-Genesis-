# AIContentApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**aiGenerateImageDescriptionPost**](AIContentApi.md#aiGenerateImageDescriptionPost) | **POST** ai/generate/image-description | Generate image description using AI |
| [**aiGenerateTextPost**](AIContentApi.md#aiGenerateTextPost) | **POST** ai/generate/text | Generate text using AI |



Generate image description using AI

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.ai.*
//import dev.aurakai.auraframefx.api.ai.infrastructure.*
//import dev.aurakai.auraframefx.api.ai.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(AIContentApi::class.java)
val generateImageDescriptionRequest : GenerateImageDescriptionRequest =  // GenerateImageDescriptionRequest | 

val result : GenerateImageDescriptionResponse = webService.aiGenerateImageDescriptionPost(generateImageDescriptionRequest)
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **generateImageDescriptionRequest** | [**GenerateImageDescriptionRequest**](GenerateImageDescriptionRequest.md)|  | |

### Return type

[**GenerateImageDescriptionResponse**](GenerateImageDescriptionResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


Generate text using AI

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.ai.*
//import dev.aurakai.auraframefx.api.ai.infrastructure.*
//import dev.aurakai.auraframefx.api.ai.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(AIContentApi::class.java)
val generateTextRequest : GenerateTextRequest =  // GenerateTextRequest | 

val result : GenerateTextResponse = webService.aiGenerateTextPost(generateTextRequest)
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **generateTextRequest** | [**GenerateTextRequest**](GenerateTextRequest.md)|  | |

### Return type

[**GenerateTextResponse**](GenerateTextResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

