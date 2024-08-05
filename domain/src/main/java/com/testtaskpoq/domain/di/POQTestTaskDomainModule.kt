package com.testtaskpoq.domain.di

import com.testtaskpoq.domain.GetListOfReposUseCase
import com.testtaskpoq.domain.repositories.ListOfReposRepository
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
    fun provideGetListOfReposUseCase(repository: ListOfReposRepository): GetListOfReposUseCase {
        return GetListOfReposUseCase(repository)
    }
}