package com.majorkik.tmdb.data.resource

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Resource("/tv")
internal class TV {

    @Resource("popular")
    class Popular(val parent: TV = TV())

    @Resource("{id}")
    @Serializable
    class Id(
        val parent: TV = TV(),
        val id: Int,
        @SerialName("append_to_response") val appendToResponse: String? = null,
    )
}
