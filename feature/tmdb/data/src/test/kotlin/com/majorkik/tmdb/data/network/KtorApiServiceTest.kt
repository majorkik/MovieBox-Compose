package com.majorkik.tmdb.data.network

import com.majorkik.tmdb.data.util.simpleMockEngine
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.ktor.serialization.JsonConvertException

class KtorApiServiceTest : StringSpec({

    "Serializing json to TVDetailsRespoonse model" {
        val mockEngine = simpleMockEngine("/tv_details.json")
        val api = KtorApiServiceImpl(engine = mockEngine)

        shouldNotThrow<JsonConvertException> { api.getTV(1) }
    }
})
