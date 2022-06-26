package com.example.acronymsapp.core.util

sealed class  ResultStatus<T>(
    val data: T? = null,
    val message: String? = null,
) {
    data class Success<T>(private val successData: T?) : ResultStatus<T>(successData)
    data class Error<T>(private val errorMessage: String?, private val errorData: T? = null) :
        ResultStatus<T>(errorData, errorMessage)
    class Loading<T> : ResultStatus<T>()
}