package com.testtaskpoq.domain.repositories

import com.testtaskpoq.domain.entity.ListOfReposEntity

interface ListOfReposRepository {
    suspend fun getListOfRepos(): List<ListOfReposEntity>
}