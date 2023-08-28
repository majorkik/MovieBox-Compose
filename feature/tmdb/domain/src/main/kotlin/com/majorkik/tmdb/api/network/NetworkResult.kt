package com.majorkik.tmdb.api.network

/** Wrapper class for request execution states */
sealed class NetworkResult<out T, out E> {

    /** A request that resulted in a response with a 2xx status code with a body of type [T]. */
    data class Success<out T>(val data: T, val code: Int) : NetworkResult<T, Nothing>()

    /** A request that resulted in a response with a non-2xx status code. */
    data class Error<out E>(val data: E?, val code: Int) : NetworkResult<Nothing, E>()

    /**
     * A request that resulted in an error different from an IO or Server error.
     *
     * An example of such an error is JSON parsing exception thrown by a serialization library.
     */
    data class Exception(val exception: Throwable) : NetworkResult<Nothing, Nothing>() {
        private val message: String? = exception.localizedMessage
        override fun toString(): String = "[NetworkResult.Exception](message=$message)"
    }
}
