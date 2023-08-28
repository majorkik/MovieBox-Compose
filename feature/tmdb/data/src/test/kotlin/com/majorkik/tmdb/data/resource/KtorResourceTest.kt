package com.majorkik.tmdb.data.resource

import com.majorkik.tmdb.data.network.KtorApiServiceImpl
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import io.ktor.client.plugins.resources.href

class KtorResourceTest : StringSpec({

    "All resources have been successfully generated" {
        val api = KtorApiServiceImpl(OkHttpEngine(OkHttpConfig()))

        api.client.href(TV.Popular()) shouldBe "/tv/popular"
        api.client.href(TV.Id(id = 1)) shouldBe "/tv/1"
    }
})
