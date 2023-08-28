package com.majorkik.tmdb.data.repository

import com.majorkik.tmdb.data.generator.fakeTVDetailsResponse
import com.majorkik.tmdb.data.network.ApiService
import com.majorkik.tmdb.data.network.KtorApiService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class TVRepositoryTest : BehaviorSpec({

    val apiService: ApiService = mockk()
    val apiKtor: KtorApiService = mockk()

    given("TV details response") {
        val tvDetailsResponse = fakeTVDetailsResponse(1)
        coEvery { apiKtor.getTV(any()) } returns tvDetailsResponse

        `when`("get TV from repository") {
            val repository = TVsRepositoryImpl(api = apiService, ktorApi = apiKtor)

            then("DTO mapped to domain") {
                val tvDetails = repository.getTV(tvDetailsResponse.id)

                coVerify { apiKtor.getTV(any()) }

                tvDetails.id shouldBe tvDetailsResponse.id
            }
        }
    }
})
