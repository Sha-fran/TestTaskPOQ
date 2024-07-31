package com.testtaskpoq.core.network

import com.testtaskpoq.core.network.models.ListOfOrganisationsModel
import retrofit2.http.GET

const val END_POINT = "orgs/square/repos"

interface POQTestTaskApiInterface {
    @GET(END_POINT)
    suspend fun getListOfOrganisations(): List<ListOfOrganisationsModel>
}