package com.majorkik.tmdb.api.model.image

import com.majorkik.tmdb.api.UrlConstants

@JvmInline
value class Profile(val value: String) {
    @Suppress("unused")
    enum class Size(val path: String) {
        Width45("w45"),
        Width185("w185"),
        Width300("w300"),
        Original("original"),
    }

    fun build(size: Size = Size.Original): String {
        return UrlConstants.IMAGE_URL + size.path + value
    }
}

fun String.toProfile() = Profile(value = this)
