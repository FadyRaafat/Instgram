package com.fady.instgramclone.data.models


import com.google.gson.annotations.SerializedName

class PhotosResponse : ArrayList<Photo>()
data class Photo(
    @SerializedName("albumId") val albumId: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?
)
