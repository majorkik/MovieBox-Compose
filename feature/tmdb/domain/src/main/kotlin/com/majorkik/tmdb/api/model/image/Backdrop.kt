package com.majorkik.tmdb.api.model.image

import com.majorkik.tmdb.api.UrlConstants

@JvmInline
value class Backdrop(val value: String) {
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

fun String.toBackdrop() = Backdrop(value = this)

val Backdrop.small
    get() = build(Backdrop.Size.Width300)
val Backdrop.medium
    get() = build(Backdrop.Size.Width780)
val Backdrop.large
    get() = build(Backdrop.Size.Width1280)
val Backdrop.original
    get() = build(Backdrop.Size.Original)
