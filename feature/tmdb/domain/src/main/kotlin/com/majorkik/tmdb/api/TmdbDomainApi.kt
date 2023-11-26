package com.majorkik.tmdb.api

import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCase

interface TmdbDomainApi {

    fun getTVDetailsUseCase(): GetTVDetailsUseCase
}

internal class TmdbDomainApiImp : TmdbDomainApi {

    override fun getTVDetailsUseCase(): GetTVDetailsUseCase {
        return TmdbDomainFeature.component().getTVDetailsUseCase
    }
}
