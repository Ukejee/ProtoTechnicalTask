package com.ukejee.prototechnicaltask.data.api.videos.endpoint

import com.ukejee.prototechnicaltask.data.api.videos.model.ApiGetVideoResponse
import retrofit2.http.GET

interface VideoEndpoint {

    @GET("v6/interview")
    suspend fun getAllVideos(): List<ApiGetVideoResponse>
}