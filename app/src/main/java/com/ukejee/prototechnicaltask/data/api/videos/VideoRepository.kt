package com.ukejee.prototechnicaltask.data.api.videos

import com.ukejee.prototechnicaltask.data.api.Resource
import com.ukejee.prototechnicaltask.data.api.videos.datasource.VideoNetworkDataSource
import com.ukejee.prototechnicaltask.data.api.videos.model.ApiGetVideoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class VideoRepository(
    private val videoNetworkDataSource: VideoNetworkDataSource
) : VideoRepositoryContract{

    override suspend fun getAllVideos(): Resource<List<ApiGetVideoResponse>?> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.success(videoNetworkDataSource.getAllVideos())
            } catch (ex: Exception) {
                Resource.error(null, (ex as Throwable).message ?: "Error Occurred!")
            }
        }
    }

}