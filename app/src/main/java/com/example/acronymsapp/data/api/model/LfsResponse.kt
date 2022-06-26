package com.example.acronymsapp.data.api.model

data class LfsResponse(
    val lf: String,
    val freq: Int,
    val since: Int,
    val vars: List<LfsResponse>? = null
)