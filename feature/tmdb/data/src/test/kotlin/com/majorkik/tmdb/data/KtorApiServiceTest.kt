package com.majorkik.tmdb.data

import com.majorkik.tmdb.data.network.KtorApiServiceImpl
import com.majorkik.tmdb.data.resource.TV
import com.majorkik.tmdb.data.util.simpleMockEngine
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import io.ktor.client.plugins.resources.href
import io.ktor.serialization.JsonConvertException

class KtorApiServiceTest : StringSpec({

    "All resources have been successfully generated" {
        val api = KtorApiServiceImpl(OkHttpEngine(OkHttpConfig()))

        api.client.href(TV.Popular()) shouldBe "/tv/popular"
        api.client.href(TV.Id(id = 1)) shouldBe "/tv/1"
    }

    "Serializing json to TVDetailsRespoonse model" {
        val mockEngine = simpleMockEngine("/tv_details.json")
        val api = KtorApiServiceImpl(engine = mockEngine)

        shouldNotThrow<JsonConvertException> { api.getTV(1) }
    }
})
