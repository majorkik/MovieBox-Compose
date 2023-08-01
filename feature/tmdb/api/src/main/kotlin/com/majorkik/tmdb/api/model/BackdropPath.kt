package com.majorkik.tmdb.api.model

import com.majorkik.tmdb.api.UrlConstants

@JvmInline
value class BackdropPath(val value: String) {
    enum class Size(val path: String) {
        Width300("w300"),
        Width780("w780"),
        Width1280("w1280"),
        Original("original"),
    }

    fun build(size: Size = Size.Original): String {
        return UrlConstants.IMAGE_URL + size.path + value
    }
}

fun String.toBackdropPath() = BackdropPath(value = this)

val BackdropPath.small
    get() = build(BackdropPath.Size.Width300)
val BackdropPath.medium
    get() = build(BackdropPath.Size.Width780)
val BackdropPath.large
    get() = build(BackdropPath.Size.Width1280)
val BackdropPath.original
    get() = build(BackdropPath.Size.Original)
