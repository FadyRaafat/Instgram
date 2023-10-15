package com.fady.instgramclone.domain.usecases

import com.fady.instgramclone.data.models.UsersResponse
import com.fady.instgramclone.domain.repository.UsersRepository
import com.fady.instgramclone.presentation.utils.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    operator fun invoke(): Flow<Resource<UsersResponse>> = flow {
        emit(Resource.Loading)
        emit(usersRepository.getUsers())
    }.flowOn(Dispatchers.IO)
}
