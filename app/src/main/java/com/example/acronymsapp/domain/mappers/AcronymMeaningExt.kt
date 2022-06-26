package com.example.acronymsapp.domain.mappers

import com.example.acronymsapp.data.api.model.AcronymMeaningResponse
import com.example.acronymsapp.data.api.model.LfsResponse
import com.example.acronymsapp.domain.model.AcronymMeaningModel
import com.example.acronymsapp.domain.model.AcronymsMeaningModel

fun AcronymMeaningResponse.toDomain(sf: String): AcronymsMeaningModel {
    val lfList = mutableListOf<AcronymMeaningModel>()
    lfs.forEach { lfItem ->
        lfList.add(lfItem.toDomain(lfItem.lf))
    }
    return AcronymsMeaningModel(sf, lfList)
}

fun LfsResponse.toDomain(lf: String): AcronymMeaningModel = AcronymMeaningModel(lf)