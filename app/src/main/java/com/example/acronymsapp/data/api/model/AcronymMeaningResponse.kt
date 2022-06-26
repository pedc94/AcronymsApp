package com.example.acronymsapp.data.api.model

data class AcronymMeaningResponse(
    val sf: String,
    val lfs: List<LfsResponse>
)