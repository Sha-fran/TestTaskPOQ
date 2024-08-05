package com.testtaskpoq.domain

import com.testtaskpoq.domain.entity.ListOfReposEntity
import com.testtaskpoq.domain.repositories.ListOfReposRepository
import javax.inject.Inject

open class GetListOfReposUseCase @Inject constructor(
    private val listOfReposRepository: ListOfReposRepository
) {
    suspend fun getListOfRepos(): List<ListOfReposEntity> =
        listOfReposRepository.getListOfRepos()
}