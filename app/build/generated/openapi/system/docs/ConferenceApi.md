# ConferenceApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**conferenceRoomsPost**](ConferenceApi.md#conferenceRoomsPost) | **POST** conference/rooms | Create a new conference room |



Create a new conference room

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.system.*
//import dev.aurakai.auraframefx.api.system.infrastructure.*
//import dev.aurakai.auraframefx.api.system.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(ConferenceApi::class.java)
val conferenceRoomCreateRequest : ConferenceRoomCreateRequest =  // ConferenceRoomCreateRequest | 

val result : ConferenceRoom = webService.conferenceRoomsPost(conferenceRoomCreateRequest)
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **conferenceRoomCreateRequest** | [**ConferenceRoomCreateRequest**](ConferenceRoomCreateRequest.md)|  | |

### Return type

[**ConferenceRoom**](ConferenceRoom.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

