package com.testtaskpoq.data.di

import com.testtaskpoq.core.network.POQTestTaskApiInterface
import com.testtaskpoq.data.ListOfOrganisationsRepositoryImpl
import com.testtaskpoq.domain.repositories.ListOfOrganisationsRepository
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
    fun provideListOfOrganisationsRepository(remote: POQTestTaskApiInterface): ListOfOrganisationsRepository =
        ListOfOrganisationsRepositoryImpl(remote)
}