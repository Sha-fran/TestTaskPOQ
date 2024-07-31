package com.testtaskpoq.core.network.di

import com.testtaskpoq.core.network.POQApiClient
import com.testtaskpoq.core.network.POQTestTaskApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestTaskPOQNetworkModule {

    @Provides
    @Singleton
    fun getPOQApiClient() = POQApiClient()

    @Provides
    @Singleton
    fun providePOQTestTaskApiInterface(poqApiClient: POQApiClient): POQTestTaskApiInterface {
        return poqApiClient.poqApiClient.create(POQTestTaskApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
}