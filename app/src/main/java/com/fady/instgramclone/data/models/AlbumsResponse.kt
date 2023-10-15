package com.fady.instgramclone.data.models


import com.google.gson.annotations.SerializedName

class AlbumsResponse : ArrayList<Album>()

data class Album(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("userId") val userId: Int?
)
