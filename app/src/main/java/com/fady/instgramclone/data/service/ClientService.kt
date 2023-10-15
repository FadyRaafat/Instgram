package com.fady.instgramclone.data.service

import com.fady.instgramclone.data.models.AlbumsResponse
import com.fady.instgramclone.data.models.PhotosResponse
import com.fady.instgramclone.data.models.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientService {

    @GET("users")
    suspend fun getUsers(): UsersResponse

    @GET("albums")
    suspend fun getAlbums(
        @Query("userId") userId: Int
    ): AlbumsResponse

    @GET("photos")
    suspend fun getPhotos(
        @Query("albumId") albumId: Int
    ): PhotosResponse
}