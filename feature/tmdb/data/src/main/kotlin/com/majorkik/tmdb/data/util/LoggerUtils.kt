package com.majorkik.tmdb.data.util

import com.majorkik.tmdb.data.logger
import com.slack.eithernet.ApiResult

fun <T : Any> ApiResult.Failure<T>.printLog(): Unit = when (this) {
    is ApiResult.Failure.NetworkFailure -> this.error.localizedMessage
    is ApiResult.Failure.UnknownFailure -> this.error.localizedMessage
    is ApiResult.Failure.HttpFailure -> "HttpFailure: ${this.error?.toString()}, code: ${this.code}"
    is ApiResult.Failure.ApiFailure -> "ApiFailure: ${this.error?.toString()}"
}.run(logger::error)
