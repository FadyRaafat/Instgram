package com.fady.instgramclone.domain.repository

import com.fady.instgramclone.data.models.AlbumsResponse
import com.fady.instgramclone.data.models.PhotosResponse
import com.fady.instgramclone.presentation.utils.common.Resource

interface PhotosRepository {

    suspend fun getAlbums() : Resource<AlbumsResponse>

    suspend fun getPhotos(albumId: Int) : Resource<PhotosResponse>
}