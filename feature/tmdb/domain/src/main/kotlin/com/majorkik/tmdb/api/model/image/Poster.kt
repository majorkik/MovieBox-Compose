package com.majorkik.tmdb.api.model.image

import com.majorkik.tmdb.api.UrlConstants

@JvmInline
value class Poster(val value: String) {
    @Suppress("unused")
    enum class Size(val path: String) {
        Width92("w92"),
        Width154("w154"),
        Width185("w185"),
        Width342("w342"),
        Width500("w500"),
        Width780("w780"),
        Original("original"),
    }

    fun build(size: Size = Size.Original): String {
        return UrlConstants.IMAGE_URL + size.path + value
    }
}

fun String.toPoster() = Poster(value = this)
