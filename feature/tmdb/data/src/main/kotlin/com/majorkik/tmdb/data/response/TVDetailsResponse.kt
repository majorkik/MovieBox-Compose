package com.majorkik.tmdb.data.response

import com.majorkik.tmdb.api.model.TVDetails
import com.majorkik.tmdb.api.model.image.toBackdrop
import com.majorkik.tmdb.api.model.image.toLogo
import com.majorkik.tmdb.api.model.image.toPoster
import com.majorkik.tmdb.api.model.image.toProfile
import com.majorkik.tmdb.api.model.image.toStill
import com.majorkik.tmdb.data.util.tryParseDateFromAPI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TVDetailsResponse(
    @SerialName("adult") val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("created_by") val createdBy: List<CreatedBy>,
    @SerialName("episode_run_time") val episodeRunTime: List<Int>,
    @SerialName("first_air_date") val firstAirDate: String?,
    @SerialName("genres") val genres: List<Genre>,
    @SerialName("homepage") val homepage: String?,
    @SerialName("id") val id: Int,
    @SerialName("in_production") val inProduction: Boolean,
    @SerialName("languages") val languages: List<String>,
    @SerialName("last_air_date") val lastAirDate: String?,
    @SerialName("last_episode_to_air") val episode: Episode,
    @SerialName("name") val name: String,
    @SerialName("networks") val networks: List<Network>,
    @SerialName("next_episode_to_air") val nextEpisodeToAir: Episode?,
    @SerialName("number_of_episodes") val numberOfEpisodes: Int,
    @SerialName("number_of_seasons") val numberOfSeasons: Int,
    @SerialName("origin_country") val originCountry: List<String>,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_name") val originalName: String,
    @SerialName("overview") val overview: String?,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompany>,
    @SerialName("production_countries") val productionCountries: List<ProductionCountry>,
    @SerialName("seasons") val seasons: List<Season>,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguage>,
    @SerialName("status") val status: String,
    @SerialName("tagline") val tagline: String?,
    @SerialName("type") val type: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("images") val images: Images?,
) {
    @Serializable
    data class CreatedBy(
        @SerialName("credit_id") val creditId: String,
        @SerialName("gender") val gender: Int,
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String,
        @SerialName("profile_path") val profilePath: String?,
    )

    @Serializable
    data class Genre(@SerialName("id") val id: Int, @SerialName("name") val name: String)

    @Serializable
    data class Episode(
        @SerialName("air_date") val airDate: String,
        @SerialName("episode_number") val episodeNumber: Int,
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String,
        @SerialName("overview") val overview: String,
        @SerialName("production_code") val productionCode: String,
        @SerialName("runtime") val runtime: Int?,
        @SerialName("season_number") val seasonNumber: Int,
        @SerialName("show_id") val showId: Int,
        @SerialName("still_path") val stillPath: String?,
        @SerialName("vote_average") val voteAverage: Double,
        @SerialName("vote_count") val voteCount: Int,
    )

    @Serializable
    data class Network(
        @SerialName("id") val id: Int,
        @SerialName("logo_path") val logoPath: String,
        @SerialName("name") val name: String,
        @SerialName("origin_country") val originCountry: String?,
    )

    @Serializable
    data class ProductionCompany(
        @SerialName("id") val id: Int,
        @SerialName("logo_path") val logoPath: String?,
        @SerialName("name") val name: String,
        @SerialName("origin_country") val originCountry: String,
    )

    @Serializable
    data class ProductionCountry(
        @SerialName("iso_3166_1") val iso31661: String,
        @SerialName("name") val name: String,
    )

    @Serializable
    data class Season(
        @SerialName("air_date") val airDate: String,
        @SerialName("episode_count") val episodeCount: Int,
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String,
        @SerialName("overview") val overview: String,
        @SerialName("poster_path") val posterPath: String?,
        @SerialName("season_number") val seasonNumber: Int,
        @SerialName("vote_average") val voteAverage: Double,
    )

    @Serializable
    data class SpokenLanguage(
        @SerialName("iso_639_1") val iso6391: String,
        @SerialName("name") val name: String,
    )

    @Serializable
    data class Images(
        @SerialName("backdrops") val backdrops: List<Image>,
        @SerialName("posters") val posters: List<Image>,
    ) {
        @Serializable
        data class Image(
            @SerialName("aspect_ratio") val aspectRatio: Double?,
            @SerialName("file_path") val filePath: String?,
        )
    }
}

internal fun TVDetailsResponse.toDomain() = TVDetails(
    adult = adult,
    backdrop = backdropPath?.toBackdrop(),
    createdBy = createdBy.map(TVDetailsResponse.CreatedBy::toDomain),
    episodeRunTime = episodeRunTime,
    firstAirDate = firstAirDate?.let(::tryParseDateFromAPI),
    genres = genres.map(TVDetailsResponse.Genre::toDomain),
    homepage = homepage,
    id = id,
    inProduction = inProduction,
    languages = languages,
    lastAirDate = lastAirDate?.let(::tryParseDateFromAPI),
    episode = episode.toDomain(),
    name = name,
    networks = networks.map(TVDetailsResponse.Network::toDomain),
    nextEpisodeToAir = nextEpisodeToAir?.toDomain(),
    numberOfEpisodes = numberOfEpisodes,
    numberOfSeasons = numberOfSeasons,
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    originalName = originalName,
    overview = overview,
    popularity = popularity,
    poster = posterPath?.toPoster(),
    productionCompanies = productionCompanies.map(TVDetailsResponse.ProductionCompany::toDomain),
    productionCountries = productionCountries.map(TVDetailsResponse.ProductionCountry::toDomain),
    seasons = seasons.map(TVDetailsResponse.Season::toDomain),
    spokenLanguages = spokenLanguages.map(TVDetailsResponse.SpokenLanguage::toDomain),
    status = status,
    tagline = tagline,
    type = type,
    voteAverage = voteAverage,
    voteCount = voteCount,
    posters = images?.posters?.mapNotNull { it.filePath?.toPoster() }.orEmpty(),
    backdrops = images?.backdrops?.mapNotNull { it.filePath?.toBackdrop() }.orEmpty(),
)

internal fun TVDetailsResponse.CreatedBy.toDomain() = TVDetails.CreatedBy(
    creditId = creditId,
    gender = gender,
    id = id,
    name = name,
    profile = profilePath?.toProfile(),
)

internal fun TVDetailsResponse.Genre.toDomain() = TVDetails.Genre(id = id, name = name)

internal fun TVDetailsResponse.Episode.toDomain() = TVDetails.Episode(
    airDate = airDate.let(::tryParseDateFromAPI),
    episodeNumber = episodeNumber,
    id = id,
    name = name,
    overview = overview,
    productionCode = productionCode,
    runtime = runtime,
    seasonNumber = seasonNumber,
    showId = showId,
    still = stillPath?.toStill(),
    voteAverage = voteAverage,
    voteCount = voteCount,
)

internal fun TVDetailsResponse.Network.toDomain() = TVDetails.Network(
    id = id,
    logo = logoPath.toLogo(),
    name = name,
    originCountry = originCountry,
)

internal fun TVDetailsResponse.ProductionCompany.toDomain() = TVDetails.ProductionCompany(
    id = id,
    logo = logoPath?.toLogo(),
    name = name,
    originCountry = originCountry,
)

internal fun TVDetailsResponse.ProductionCountry.toDomain() = TVDetails.ProductionCountry(
    iso31661 = iso31661,
    name = name,
)

internal fun TVDetailsResponse.Season.toDomain() = TVDetails.Season(
    airDate = airDate.let(::tryParseDateFromAPI),
    episodeCount = episodeCount,
    id = id,
    name = name,
    overview = overview,
    poster = posterPath?.toPoster(),
    seasonNumber = seasonNumber,
    voteAverage = voteAverage,
)

internal fun TVDetailsResponse.SpokenLanguage.toDomain() = TVDetails.SpokenLanguage(
    iso6391 = iso6391,
    name = name,
)
