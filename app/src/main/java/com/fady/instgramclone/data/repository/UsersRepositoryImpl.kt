package com.fady.instgramclone.data.repository

import com.fady.instgramclone.data.datasource.InstgramDataSource
import com.fady.instgramclone.data.models.UsersResponse
import com.fady.instgramclone.data.preferences.AppLocalDataSource
import com.fady.instgramclone.domain.repository.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val productsRemoteDataSource: InstgramDataSource
) : UsersRepository {

    override suspend fun getUsers() = productsRemoteDataSource.getUsers()

    override suspend fun getUserDetails() = AppLocalDataSource.userProfile
}