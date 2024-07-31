package com.testtaskpoq.domain.di

import com.testtaskpoq.domain.GetListOfOrganisationsUseCase
import com.testtaskpoq.domain.repositories.ListOfOrganisationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object POQTestTaskDomainModule {

    @Singleton
    @Provides
    fun provideGetListOfOrganisationsUseCase(repository: ListOfOrganisationsRepository): GetListOfOrganisationsUseCase {
        return GetListOfOrganisationsUseCase(repository)
    }
}