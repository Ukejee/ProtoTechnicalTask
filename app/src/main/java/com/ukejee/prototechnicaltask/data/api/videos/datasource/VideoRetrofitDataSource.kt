package com.ukejee.prototechnicaltask.data.api.videos.datasource

import com.ukejee.prototechnicaltask.data.api.videos.endpoint.VideoEndpoint
import com.ukejee.prototechnicaltask.data.api.videos.model.ApiGetVideoResponse

class VideoRetrofitDataSource(
    private val videoEndpoint: VideoEndpoint
) : VideoNetworkDataSource{

    override suspend fun getAllVideos(): List<ApiGetVideoResponse> {
        return videoEndpoint.getAllVideos()
    }
}