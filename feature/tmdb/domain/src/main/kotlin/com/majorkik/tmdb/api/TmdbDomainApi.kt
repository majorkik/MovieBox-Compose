package com.majorkik.tmdb.api

import com.majorkik.tmdb.api.usecase.GetMovieDetailsUseCase
import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCase

interface TmdbDomainApi {

    fun getTVDetailsUseCase(): GetTVDetailsUseCase
    fun getMovieDetailsUseCase(): GetMovieDetailsUseCase
}

internal class TmdbDomainApiImp : TmdbDomainApi {

    override fun getTVDetailsUseCase(): GetTVDetailsUseCase {
        return TmdbDomainFeature.component().getTVDetailsUseCase
    }

    override fun getMovieDetailsUseCase(): GetMovieDetailsUseCase {
        return TmdbDomainFeature.component().getMovieDetailsUseCase
    }
}
