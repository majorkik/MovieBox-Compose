package com.majorkik.tmdb.api.model

import com.majorkik.tmdb.api.model.image.Backdrop
import com.majorkik.tmdb.api.model.image.Logo
import com.majorkik.tmdb.api.model.image.Poster
import com.majorkik.tmdb.api.model.image.Profile
import com.majorkik.tmdb.api.model.image.Still
import com.soywiz.klock.Date

data class TVDetails(
    val adult: Boolean,
    val backdrop: Backdrop?,
    val createdBy: List<CreatedBy>,
    val episodeRunTime: List<Int>,
    val firstAirDate: Date?,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: Date?,
    val episode: Episode,
    val name: String,
    val networks: List<Network>,
    val nextEpisodeToAir: Episode?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String?,
    val popularity: Double,
    val poster: Poster?,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val seasons: List<Season>,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String?,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int,
    val posters: List<Poster>,
    val backdrops: List<Backdrop>,
) {
    data class CreatedBy(
        val creditId: String,
        val gender: Int,
        val id: Int,
        val name: String,
        val profile: Profile?,
    )

    data class Genre(val id: Int, val name: String)

    data class Episode(
        val airDate: Date?,
        val episodeNumber: Int,
        val id: Int,
        val name: String,
        val overview: String,
        val productionCode: String,
        val runtime: Int?,
        val seasonNumber: Int,
        val showId: Int,
        val still: Still?,
        val voteAverage: Double,
        val voteCount: Int,
    )

    data class Network(
        val id: Int,
        val logo: Logo?,
        val name: String,
        val originCountry: String?,
    )

    data class ProductionCompany(
        val id: Int,
        val logo: Logo?,
        val name: String,
        val originCountry: String,
    )

    data class ProductionCountry(
        val iso31661: String,
        val name: String,
    )

    data class Season(
        val airDate: Date?,
        val episodeCount: Int,
        val id: Int,
        val name: String,
        val overview: String,
        val poster: Poster?,
        val seasonNumber: Int,
        val voteAverage: Double,
    )

    data class SpokenLanguage(
        val iso6391: String,
        val name: String,
    )
}

@Suppress("Detekt.LongMethod", "Detekt.MagicNumber")
fun tvDetailsPreview() = TVDetails(
    adult = false,
    backdrop = null,
    createdBy = listOf(),
    episodeRunTime = listOf(),
    firstAirDate = null,
    genres = listOf(),
    homepage = null,
    id = 8829,
    inProduction = false,
    languages = listOf(),
    lastAirDate = null,
    episode = TVDetails.Episode(
        airDate = Date.invoke(year = 2022, month = 12, day = 28),
        episodeNumber = 9995,
        id = 5875,
        name = "Nathan Shaw",
        overview = "conceptam",
        productionCode = "wisi",
        runtime = null,
        seasonNumber = 9302,
        showId = 5780,
        still = null,
        voteAverage = 6.7,
        voteCount = 2724
    ),
    name = "Kasey Le",
    networks = listOf(),
    nextEpisodeToAir = null,
    numberOfEpisodes = 6843,
    numberOfSeasons = 3342,
    originCountry = listOf(),
    originalLanguage = "mandamus",
    originalName = "Jamar Bean",
    overview = null,
    popularity = 8.9,
    poster = null,
    productionCompanies = listOf(),
    productionCountries = listOf(),
    seasons = listOf(),
    spokenLanguages = listOf(),
    status = "sit",
    tagline = null,
    type = "aenean",
    voteAverage = 10.11,
    voteCount = 9156,
    posters = emptyList(),
    backdrops = emptyList(),
)
