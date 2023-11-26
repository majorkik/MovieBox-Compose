package com.majorkik.tmdb.data

import com.majorkik.tmdb.api.repository.TVsRepository

interface TmdbDataApi {

    fun getTVsRepository(): TVsRepository
}

internal class TmdbDataApiImp : TmdbDataApi {
    override fun getTVsRepository(): TVsRepository {
        return TmdbDataFeature.component().tvDetailsRepository
    }
}
