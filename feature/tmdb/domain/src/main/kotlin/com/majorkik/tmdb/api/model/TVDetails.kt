package com.majorkik.tmdb.api.model

import com.majorkik.tmdb.api.model.image.Backdrop
import com.majorkik.tmdb.api.model.image.Logo
import com.majorkik.tmdb.api.model.image.Poster
import com.majorkik.tmdb.api.model.image.Profile
import com.majorkik.tmdb.api.model.image.Still

data class TVDetails(
    val adult: Boolean,
    val backdrop: Backdrop?,
    val createdBy: List<CreatedBy>,
    val episodeRunTime: List<Int>,
    val firstAirDate: String?,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String?,
    val lastEpisodeToAir: LastEpisodeToAir,
    val name: String,
    val networks: List<Network>,
    val nextEpisodeToAir: String?,
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
) {
    data class CreatedBy(
        val creditId: String,
        val gender: Int,
        val id: Int,
        val name: String,
        val profile: Profile?,
    )

    data class Genre(val id: Int, val name: String)

    data class LastEpisodeToAir(
        val airDate: String,
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
        val airDate: String,
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
