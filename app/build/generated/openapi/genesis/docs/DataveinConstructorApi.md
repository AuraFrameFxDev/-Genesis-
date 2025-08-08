# DataveinConstructorApi

All URIs are relative to *https://api.genesis.aurakai.dev/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**dataveinAnalyzeRomPost**](DataveinConstructorApi.md#dataveinAnalyzeRomPost) | **POST** datavein/analyze-rom | Analyze ROM/boot.img file |



Analyze ROM/boot.img file

Performs AI-powered analysis of Android ROM or boot.img files

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.genesis.*
//import dev.aurakai.auraframefx.api.genesis.infrastructure.*
//import dev.aurakai.auraframefx.api.genesis.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(DataveinConstructorApi::class.java)
val romFile : java.io.File = BINARY_DATA_HERE // java.io.File | ROM or boot.img file to analyze
val analysisType : kotlin.String = analysisType_example // kotlin.String | Type of analysis to perform

val result : RomAnalysisResponse = webService.dataveinAnalyzeRomPost(romFile, analysisType)
```

### Parameters
| **romFile** | **java.io.File**| ROM or boot.img file to analyze | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **analysisType** | **kotlin.String**| Type of analysis to perform | [optional] [enum: security, compatibility, modification] |

### Return type

[**RomAnalysisResponse**](RomAnalysisResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

