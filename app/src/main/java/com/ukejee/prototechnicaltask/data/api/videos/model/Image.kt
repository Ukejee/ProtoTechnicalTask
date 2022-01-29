package com.ukejee.prototechnicaltask.data.api.videos.model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null
)