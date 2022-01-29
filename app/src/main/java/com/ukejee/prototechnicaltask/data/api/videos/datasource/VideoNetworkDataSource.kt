package com.ukejee.prototechnicaltask.data.api.videos.datasource

import com.ukejee.prototechnicaltask.data.api.videos.model.ApiGetVideoResponse

interface VideoNetworkDataSource {

    suspend fun getAllVideos(): List<ApiGetVideoResponse>
}