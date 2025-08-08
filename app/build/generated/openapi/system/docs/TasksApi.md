# TasksApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**tasksSchedulePost**](TasksApi.md#tasksSchedulePost) | **POST** tasks/schedule | Schedule a new task |
| [**tasksTaskIdGet**](TasksApi.md#tasksTaskIdGet) | **GET** tasks/{taskId} | Get task status |



Schedule a new task

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.system.*
//import dev.aurakai.auraframefx.api.system.infrastructure.*
//import dev.aurakai.auraframefx.api.system.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(TasksApi::class.java)
val taskScheduleRequest : TaskScheduleRequest =  // TaskScheduleRequest | 

val result : TaskStatus = webService.tasksSchedulePost(taskScheduleRequest)
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **taskScheduleRequest** | [**TaskScheduleRequest**](TaskScheduleRequest.md)|  | |

### Return type

[**TaskStatus**](TaskStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


Get task status

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.system.*
//import dev.aurakai.auraframefx.api.system.infrastructure.*
//import dev.aurakai.auraframefx.api.system.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(TasksApi::class.java)
val taskId : kotlin.String = taskId_example // kotlin.String | ID of the task to check

val result : TaskStatus = webService.tasksTaskIdGet(taskId)
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **taskId** | **kotlin.String**| ID of the task to check | |

### Return type

[**TaskStatus**](TaskStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

