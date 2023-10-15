package com.fady.instgramclone.di

import com.fady.instgramclone.data.datasource.InstgramDataSource
import com.fady.instgramclone.data.repository.PhotosRepositoryImpl
import com.fady.instgramclone.data.repository.UsersRepositoryImpl
import com.fady.instgramclone.data.service.ClientService
import com.fady.instgramclone.domain.repository.PhotosRepository
import com.fady.instgramclone.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InstgramModule {

    @Provides
    @Singleton
    fun provideInstgramDataSource(apiService: ClientService) = InstgramDataSource(apiService)


    @Provides
    @Singleton
    fun provideUserRepository(dataSource: InstgramDataSource): UsersRepository =
        UsersRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun providePhotosRepository(dataSource: InstgramDataSource): PhotosRepository =
        PhotosRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideInstgramServices(retrofit: Retrofit): ClientService =
        retrofit.create(ClientService::class.java)
}