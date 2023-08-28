package com.majorkik.tmdb.impl.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.majorkik.tmdb.api.UrlConstants
import com.majorkik.tmdb.impl.Config
import com.slack.eithernet.ApiResultCallAdapterFactory
import com.slack.eithernet.ApiResultConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

internal val okHttpClient = OkHttpClient()

internal fun getLoggingInterceptorBodyLevel() =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

internal fun getInterceptor() =
    Interceptor.invoke { chain ->
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", Config.TMDB_API_KEY)
            .build()

        val request = chain.request().newBuilder().url(url).build()

        return@invoke chain.proceed(request)
    }

internal fun createHttpClient(): OkHttpClient {
    return okHttpClient
        .newBuilder()
        .addInterceptor(getLoggingInterceptorBodyLevel())
        .addNetworkInterceptor(getInterceptor())
        .build()
}

@ExperimentalSerializationApi
internal fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val mediaType = "application/json".toMediaType()

    val json = Json {
        explicitNulls = false
        ignoreUnknownKeys = true
        isLenient = true
    }

    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(UrlConstants.BASE_URL)
        .addConverterFactory(ApiResultConverterFactory)
        .addCallAdapterFactory(ApiResultCallAdapterFactory)
        .addConverterFactory(json.asConverterFactory(mediaType))
        .build()
}

@OptIn(ExperimentalSerializationApi::class)
internal fun createApiService(): ApiService {
    return createRetrofit(createHttpClient()).create(ApiService::class.java)
}
