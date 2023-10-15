package com.fady.instgramclone.domain.usecases

import com.fady.instgramclone.data.models.PhotosResponse
import com.fady.instgramclone.domain.repository.PhotosRepository
import com.fady.instgramclone.presentation.utils.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photosRepository: PhotosRepository
) {

    operator fun invoke(albumId: Int): Flow<Resource<PhotosResponse>> = flow {
        emit(Resource.Loading)
        emit(photosRepository.getPhotos(albumId))
    }.flowOn(Dispatchers.IO)
}