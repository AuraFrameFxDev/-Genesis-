# SystemCustomizationApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**systemLockscreenConfigGet**](SystemCustomizationApi.md#systemLockscreenConfigGet) | **GET** system/lockscreen-config | Get lock screen configuration |
| [**systemLockscreenConfigPut**](SystemCustomizationApi.md#systemLockscreenConfigPut) | **PUT** system/lockscreen-config | Update lock screen configuration |



Get lock screen configuration

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.system.*
//import dev.aurakai.auraframefx.api.system.infrastructure.*
//import dev.aurakai.auraframefx.api.system.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(SystemCustomizationApi::class.java)

val result : LockScreenConfig = webService.systemLockscreenConfigGet()
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**LockScreenConfig**](LockScreenConfig.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


Update lock screen configuration

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.system.*
//import dev.aurakai.auraframefx.api.system.infrastructure.*
//import dev.aurakai.auraframefx.api.system.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(SystemCustomizationApi::class.java)
val lockScreenConfig : LockScreenConfig =  // LockScreenConfig | 

webService.systemLockscreenConfigPut(lockScreenConfig)
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **lockScreenConfig** | [**LockScreenConfig**](LockScreenConfig.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

