package com.ukejee.prototechnicaltask.data.api.videos

import com.ukejee.prototechnicaltask.data.api.videos.model.ApiGetVideoResponse

interface VideoRepositoryContract {

    suspend fun getAllVideos(): List<ApiGetVideoResponse>
}