package com.fady.instgramclone.data.datasource

import com.fady.instgramclone.data.service.ClientService
import com.fady.instgramclone.presentation.utils.base.BaseRemoteDataSource
import javax.inject.Inject

class InstgramDataSource @Inject constructor(
    private val apiService: ClientService
) : BaseRemoteDataSource() {

    suspend fun getUsers() = safeApiCall {
        apiService.getUsers()
    }

    suspend fun getAlbums(userId: Int) = safeApiCall {
        apiService.getAlbums(userId)
    }

    suspend fun getPhotos(albumId: Int) = safeApiCall {
        apiService.getPhotos(albumId)
    }

}