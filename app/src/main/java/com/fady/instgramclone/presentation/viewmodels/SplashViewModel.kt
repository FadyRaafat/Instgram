package com.fady.instgramclone.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.fady.instgramclone.data.models.UsersResponse
import com.fady.instgramclone.data.preferences.AppLocalDataSource
import com.fady.instgramclone.domain.usecases.GetUsersUseCase
import com.fady.instgramclone.presentation.utils.base.BaseViewModel
import com.fady.instgramclone.presentation.utils.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : BaseViewModel() {

    // Get Users
    private val _getUsersSuccess = MutableSharedFlow<Boolean>()
    val getUsersSuccess = _getUsersSuccess

    fun getUsers() {
        getUsersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    saveRandomUser(result.value)
                    _getUsersSuccess.emit(true)
                }

                is Resource.Failure -> {
                    _getUsersSuccess.emit(false)
                    showApiError.value = result
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun saveRandomUser(users: UsersResponse) =
        users.random().also { AppLocalDataSource.userProfile = it }


}