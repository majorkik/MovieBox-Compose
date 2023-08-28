package com.majorkik.tmdb.impl.network

import com.majorkik.tmdb.api.network.NetworkResult
import com.majorkik.tmdb.impl.logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Executes the request safely, handling error cases and other possible exceptions
 *
 * Note: The request must be wrapped in a Response <Model>
 *
 * @param onSuccess
 * - a lambda in which a successful response can be converted to another model
 * @param onError
 * - a lambda in which the error can be converted to another model
 */
@Suppress("detekt.TooGenericExceptionCaught", "detekt.SwallowedException")
internal suspend fun <T, M, E> safeRequest(
    call: suspend () -> Response<T>,
    onSuccess: suspend (T?) -> M?,
    onError: suspend (ResponseBody?) -> E,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): NetworkResult<M, E> =
    try {
        withContext(ioDispatcher) {
            val response = call()
            val body = onSuccess(response.body())
            val code = response.code()
            val errorBody = response.errorBody()

            if (response.isSuccessful) {
                if (body != null) {
                    NetworkResult.Success(data = body, code = code)
                } else {
                    logger.error { "[SafeRequest.Error] Body is null" }

                    NetworkResult.Error(data = null, code = code)
                }
            } else {
                logger.error { "[SafeRequest.Error] Response not successful" }

                NetworkResult.Error(data = onError(errorBody), code = code)
            }
        }
    } catch (e: Exception) {
        logger.error { e.localizedMessage ?: "[SafeRequest.Exception] Something went wrong" }
        NetworkResult.Exception(e)
    }

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun ResponseBody.suspendString(): String? {
    return suspendCoroutine { continuation -> continuation.resume(string()) }
}
