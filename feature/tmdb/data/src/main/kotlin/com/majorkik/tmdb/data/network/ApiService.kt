package com.majorkik.tmdb.data.network

import com.majorkik.tmdb.data.response.GenresResponse
import com.majorkik.tmdb.data.response.MovieDetailsResponse
import com.majorkik.tmdb.data.response.PagedMoviesResponse
import com.majorkik.tmdb.data.response.PagedTVsResponse
import com.majorkik.tmdb.data.response.TVDetailsResponse
import com.slack.eithernet.ApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ApiService {

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") id: Int,
        @Query("append_to_response") appendToResponse: String? = null,
        @Query("language") language: String = DEFAULT_LANGUAGE,
    ): ApiResult<MovieDetailsResponse, Unit>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String = DEFAULT_LANGUAGE,
    ): Response<PagedMoviesResponse>

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(@Query("page") page: Int): Response<PagedMoviesResponse>

    @GET("tv/{tv_id}")
    suspend fun getTV(
        @Path("movie_id") id: Int,
        @Query("append_to_response") appendToResponse: String? = null,
        @Query("language") language: String = DEFAULT_LANGUAGE,
    ): TVDetailsResponse

    @GET("tv/popular")
    suspend fun getPopularTVs(
        @Query("page") page: Int,
        @Query("language") language: String = DEFAULT_LANGUAGE,
    ): Response<PagedTVsResponse>

    @GET("trending/tv/week")
    suspend fun getTrendingTVs(@Query("page") page: Int): Response<PagedTVsResponse>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): Response<GenresResponse>

    @GET("genre/tv/list")
    suspend fun getTVGenres(): Response<GenresResponse>

    private companion object {
        const val DEFAULT_LANGUAGE = "en"
    }
}
