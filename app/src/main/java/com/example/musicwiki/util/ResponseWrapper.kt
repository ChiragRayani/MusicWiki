package com.example.musicwiki.util

data class ResponseWrapper<out T>(
    val status: Status,
    val data: T?,
    val code: Int?,
    val message: String?
) {
    companion object {
        fun <T> loading(): ResponseWrapper<T> =
            ResponseWrapper(
                status = Status.LOADING,
                code = null,
                data = null,
                message = null
            )

        fun <T> success(data: T?): ResponseWrapper<T> =
            ResponseWrapper(
                status = Status.SUCCESS,
                code = null,
                data = data,
                message = null
            )

        fun <T> error(data: T?, code: Int?, message: String): ResponseWrapper<T> =
            ResponseWrapper(
                status = Status.ERROR,
                data = data,
                code = code,
                message = message
            )

    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}