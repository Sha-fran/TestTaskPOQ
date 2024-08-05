package com.testtaskpoq.data.api

import retrofit2.http.GET

const val END_POINT = "orgs/square/repos"

interface POQTestTaskApiInterface {
    @GET(END_POINT)
    suspend fun getListOfRepos(): List<com.testtaskpoq.data.models.RepoModel>
}