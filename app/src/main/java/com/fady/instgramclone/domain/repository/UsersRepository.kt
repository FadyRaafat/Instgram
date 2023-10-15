package com.fady.instgramclone.domain.repository

import com.fady.instgramclone.data.models.User
import com.fady.instgramclone.data.models.UsersResponse
import com.fady.instgramclone.presentation.utils.common.Resource

interface UsersRepository {

    suspend fun getUsers() : Resource<UsersResponse>

    suspend fun getUserDetails() : User?
}