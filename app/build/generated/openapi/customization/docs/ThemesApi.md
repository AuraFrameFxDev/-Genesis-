# ThemesApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**themeApplyPut**](ThemesApi.md#themeApplyPut) | **PUT** theme/apply | Apply a theme |
| [**themesGet**](ThemesApi.md#themesGet) | **GET** themes | Get available themes |



Apply a theme

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.customization.*
//import dev.aurakai.auraframefx.api.customization.infrastructure.*
//import dev.aurakai.auraframefx.api.customization.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(ThemesApi::class.java)
val themeApplyRequest : ThemeApplyRequest =  // ThemeApplyRequest | 

webService.themeApplyPut(themeApplyRequest)
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **themeApplyRequest** | [**ThemeApplyRequest**](ThemeApplyRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


Get available themes

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.customization.*
//import dev.aurakai.auraframefx.api.customization.infrastructure.*
//import dev.aurakai.auraframefx.api.customization.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(ThemesApi::class.java)

val result : kotlin.collections.List<Theme> = webService.themesGet()
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;Theme&gt;**](Theme.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

