package com.ukejee.prototechnicaltask.data.api.videos.model


import com.google.gson.annotations.SerializedName

data class ApiGetVideoResponse(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("duration")
    val duration: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("images")
    val images: List<Image>? = null,
    @SerializedName("releaseDate")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null
)