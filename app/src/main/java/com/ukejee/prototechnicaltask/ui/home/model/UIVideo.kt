package com.ukejee.prototechnicaltask.ui.home.model

import com.ukejee.prototechnicaltask.data.api.videos.model.ApiGetVideoResponse

data class UIVideo(
    val id: String,
    val image: String,
    val duration: String
) {

    companion object {

        private const val DATA_NOT_AVAILABLE = "Data not available"

        fun fromApiResponse(response: ApiGetVideoResponse): UIVideo {
            return UIVideo(
                response.id.toString(),
                response.images?.first { it.type.equals("thumbnail", true) }?.url
                    ?: DATA_NOT_AVAILABLE,
                response.duration ?: DATA_NOT_AVAILABLE
            )
        }
    }
}
