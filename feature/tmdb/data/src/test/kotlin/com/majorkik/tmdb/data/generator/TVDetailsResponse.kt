package com.majorkik.tmdb.data.generator

import com.majorkik.tmdb.data.response.TVDetailsResponse

internal fun fakeTVDetailsResponse(id: Int) = TVDetailsResponse(
    adult = false,
    backdropPath = null,
    createdBy = listOf(),
    episodeRunTime = listOf(),
    firstAirDate = null,
    genres = listOf(),
    homepage = null,
    id = id,
    inProduction = false,
    languages = listOf(),
    lastAirDate = null,
    episode = TVDetailsResponse.Episode(
        airDate = "2020-12-12",
        episodeNumber = 8971,
        id = 7853,
        name = "Jean Quinn",
        overview = "unum",
        productionCode = "hac",
        runtime = null,
        seasonNumber = 1969,
        showId = 9366,
        stillPath = null,
        voteAverage = 6.7,
        voteCount = 6585
    ),
    name = "Joann Pratt",
    networks = listOf(),
    nextEpisodeToAir = null,
    numberOfEpisodes = 6180,
    numberOfSeasons = 1995,
    originCountry = listOf(),
    originalLanguage = "postulant",
    originalName = "Rhea Johns",
    overview = null,
    popularity = 8.9,
    posterPath = null,
    productionCompanies = listOf(),
    productionCountries = listOf(),
    seasons = listOf(),
    spokenLanguages = listOf(),
    status = "phasellus",
    tagline = null,
    type = "malesuada",
    voteAverage = 10.11,
    voteCount = 6179,
    images = null
)