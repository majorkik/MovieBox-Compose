package com.majorkik.tmdb.data.response

import com.majorkik.tmdb.api.model.PagedTVsResult
import com.majorkik.tmdb.api.model.TV
import com.majorkik.tmdb.api.model.image.toBackdrop
import com.majorkik.tmdb.api.model.image.toPoster
import com.majorkik.tmdb.data.util.tryParseDateFromAPI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
internal data class PagedTVsResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val data: List<TV>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
) {
    @Serializable
    data class TV(
        @SerialName("adult") val adult: Boolean?,
        @SerialName("backdrop_path") val backdropPath: String?,
        @JsonNames("first_air_date", "release_date") val releaseDate: String?,
        @SerialName("genre_ids") val genreIds: List<Int>,
        @SerialName("id") val id: Int,
        @JsonNames("name", "title") val title: String,
        @SerialName("origin_country") val originCountry: List<String>?,
        @SerialName("original_language") val originalLanguage: String,
        @JsonNames("original_title", "original_name") val originalTitle: String?,
        @SerialName("overview") val overview: String,
        @SerialName("popularity") val popularity: Double,
        @SerialName("poster_path") val posterPath: String?,
        @SerialName("video") val video: Boolean?,
        @SerialName("vote_average") val voteAverage: Double,
        @SerialName("vote_count") val voteCount: Int
    )
}

internal fun PagedTVsResponse.toDomainModel() =
    PagedTVsResult(
        tvs = data.map { it.toDomainModel() },
        page = page,
        totalPages = totalPages,
        totalItems = totalResults
    )

internal fun PagedTVsResponse.TV.toDomainModel() =
    TV(
        adult = adult ?: false,
        backdrop = backdropPath?.toBackdrop(),
        genreIds = genreIds,
        id = id,
        originCountry = originCountry ?: listOf(),
        originalLanguage = originalLanguage,
        originalTitle = originalTitle ?: title,
        overview = overview,
        popularity = popularity,
        poster = posterPath?.toPoster(),
        releaseDate = releaseDate?.let(::tryParseDateFromAPI),
        title = title,
        video = video ?: false,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
