package com.fady.instgramclone.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.fady.instgramclone.data.models.AlbumsResponse
import com.fady.instgramclone.data.models.Photo
import com.fady.instgramclone.data.models.User
import com.fady.instgramclone.domain.usecases.GetAlbumsUseCase
import com.fady.instgramclone.domain.usecases.GetPhotosUseCase
import com.fady.instgramclone.domain.usecases.GetUserDetailsUseCase
import com.fady.instgramclone.presentation.utils.base.BaseViewModel
import com.fady.instgramclone.presentation.utils.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val getPhotosUseCase: GetPhotosUseCase
) : BaseViewModel() {

    // Selected album id
    private var selectedAlbumId: Int = 0
    fun setAlbumId(albumId: Int) {
        selectedAlbumId = albumId
    }


    // Get albums
    private val _albumsSuccess = MutableSharedFlow<Boolean>()
    val albumsSuccess = _albumsSuccess
    private var albumsResponse: AlbumsResponse? = null

    fun getAlbums() {
        getAlbumsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    showLoading.value = true
                }

                is Resource.Success -> {
                    showLoading.value = false
                    albumsResponse = result.value
                    _albumsSuccess.emit(true)
                }

                is Resource.Failure -> {
                    _albumsSuccess.emit(false)
                    showLoading.value = false
                    showApiError.value = result
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun getAlbumsResponse() = albumsResponse

    // Get photos
    private val _photosSuccess = MutableSharedFlow<Boolean>()
    val photosSuccess = _photosSuccess
    private var photosResponse: ArrayList<Photo>? = null

    fun getPhotos() {
        getPhotosUseCase(selectedAlbumId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    showLoading.value = true
                }

                is Resource.Success -> {
                    showLoading.value = false
                    photosResponse = result.value
                    _photosSuccess.emit(true)
                }

                is Resource.Failure -> {
                    _photosSuccess.emit(false)
                    showLoading.value = false
                    showApiError.value = result
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun getPhotosResponse() = photosResponse

    // Get user details
    fun getUserDetails(): User? {
        var userInfo: User? = null
        runBlocking(Dispatchers.IO) {
            getUserDetailsUseCase().collect { user ->
                if (user != null) {
                    userInfo = user
                }
            }
        }
        return userInfo
    }

    // Search photos
    fun searchPhotos(query: String) {
        if (query.isEmpty()) {
            getPhotos()
        } else {
            viewModelScope.launch {
                filterPhotos(query)
            }
        }
    }

    private suspend fun filterPhotos(query: String) {
        photosResponse = photosResponse?.filter { photo ->
            photo.title?.contains(query, true) ?: false
        }?.toCollection(ArrayList())
        _photosSuccess.emit(true)
    }
}
