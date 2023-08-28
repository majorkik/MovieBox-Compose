package com.majorkik.tmdb.api.model.image

import com.majorkik.tmdb.api.UrlConstants

@JvmInline
value class Still(val value: String) {
    @Suppress("unused")
    enum class Size(val path: String) {
        Width45("w92"),
        Width185("w185"),
        Width300("w300"),
        Original("original"),
    }

    fun build(size: Size = Size.Original): String {
        return UrlConstants.IMAGE_URL + size.path + value
    }
}

fun String.toStill() = Still(value = this)
