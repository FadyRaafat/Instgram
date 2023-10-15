package com.fady.instgramclone.domain.usecases

import com.fady.instgramclone.data.models.User
import com.fady.instgramclone.domain.repository.UsersRepository
import com.fady.instgramclone.presentation.utils.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    operator fun invoke(): Flow<User?> = flow {
        emit(usersRepository.getUserDetails())
    }.flowOn(Dispatchers.IO)
}