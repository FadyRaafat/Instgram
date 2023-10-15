package com.fady.instgramclone.data.repository

import com.fady.instgramclone.data.datasource.InstgramDataSource
import com.fady.instgramclone.data.preferences.AppLocalDataSource
import com.fady.instgramclone.domain.repository.PhotosRepository
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val instgramDataSource: InstgramDataSource
) : PhotosRepository {

    override suspend fun getAlbums() = instgramDataSource.getAlbums(getUserId())

    override suspend fun getPhotos(albumId: Int) = instgramDataSource.getPhotos(albumId)

    private fun getUserId() = AppLocalDataSource.getUserId()

}