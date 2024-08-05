package com.testtaskpoq.data.di

import com.testtaskpoq.core.network.POQApiClient
import com.testtaskpoq.data.api.POQTestTaskApiInterface
import com.testtaskpoq.data.ListOfReposRepositoryImpl
import com.testtaskpoq.domain.repositories.ListOfReposRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object POQTestTaskDataModule {

    @Singleton
    @Provides
    fun provideListOfReposRepository(remote: POQTestTaskApiInterface): ListOfReposRepository =
        ListOfReposRepositoryImpl(remote)

    @Provides
    @Singleton
    fun providePOQTestTaskApiInterface(poqApiClient: POQApiClient): POQTestTaskApiInterface {
        return poqApiClient.poqApiClient.create(POQTestTaskApiInterface::class.java)
    }
}