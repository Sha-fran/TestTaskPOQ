package com.testtaskpoq.data.mappers

import com.testtaskpoq.data.models.RepoModel
import com.testtaskpoq.domain.entity.ListOfReposEntity

fun RepoModel.toEntity(): ListOfReposEntity =
    ListOfReposEntity(
        nameOfRepo = this.name,
        descriptionOfRepo = this.let { description } ?: "Description is absent"
    )