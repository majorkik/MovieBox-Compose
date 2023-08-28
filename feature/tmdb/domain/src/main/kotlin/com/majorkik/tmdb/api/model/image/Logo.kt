package com.majorkik.tmdb.api.model.image

import com.majorkik.tmdb.api.UrlConstants

@JvmInline
value class Logo(val value: String) {
    @Suppress("unused")
    enum class Size(val path: String) {
        Width45("w45"),
        Width92("w92"),
        Width154("w154"),
        Width185("w185"),
        Width300("w300"),
        Width500("w500"),
        Original("original"),
    }

    fun build(size: Size = Size.Original): String {
        return UrlConstants.IMAGE_URL + size.path + value
    }
}

fun String.toLogo() = Logo(value = this)
