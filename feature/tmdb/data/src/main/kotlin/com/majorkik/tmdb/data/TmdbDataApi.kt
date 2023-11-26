package com.majorkik.tmdb.data

import com.majorkik.tmdb.api.repository.MovieDetailsRepository
import com.majorkik.tmdb.api.repository.TVsRepository

interface TmdbDataApi {

    fun getTVsRepository(): TVsRepository
    fun getMovieDetailsRepository(): MovieDetailsRepository
}

internal class TmdbDataApiImp : TmdbDataApi {
    override fun getTVsRepository(): TVsRepository {
        return TmdbDataFeature.component().tvDetailsRepository
    }

    override fun getMovieDetailsRepository(): MovieDetailsRepository {
        return TmdbDataFeature.component().movieDetailsRepository
    }
}
