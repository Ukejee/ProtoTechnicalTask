package com.ukejee.prototechnicaltask.data.api.videos

import com.ukejee.prototechnicaltask.data.api.videos.datasource.VideoNetworkDataSource
import com.ukejee.prototechnicaltask.data.api.videos.model.ApiGetVideoResponse

class VideoRepository(
    private val videoNetworkDataSource: VideoNetworkDataSource
) : VideoRepositoryContract{

    override suspend fun getAllVideos(): List<ApiGetVideoResponse> {
        return videoNetworkDataSource.getAllVideos()
    }
}