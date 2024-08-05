package com.testtaskpoq.data

import com.testtaskpoq.data.api.POQTestTaskApiInterface
import com.testtaskpoq.data.mappers.toEntity
import com.testtaskpoq.domain.entity.ListOfReposEntity
import com.testtaskpoq.domain.repositories.ListOfReposRepository
import javax.inject.Inject

class ListOfReposRepositoryImpl @Inject constructor(
    private val poqApiInterface: POQTestTaskApiInterface
) : ListOfReposRepository {

    override suspend fun getListOfRepos(): List<ListOfReposEntity> =
        poqApiInterface.getListOfRepos().map { it.toEntity() }
}