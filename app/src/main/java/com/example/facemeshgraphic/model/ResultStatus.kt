package com.example.facemeshgraphic.model

sealed class ResultStatus<out T> {
    data object Loading : ResultStatus<Nothing>()
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Failure(val throwable: Throwable) : ResultStatus<Nothing>()
}