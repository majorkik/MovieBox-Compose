package com.majorkik.tmdb.data.util

inline fun <reified T> fromResource(caller: T, filePath: String): String = T::class.java.getResource(filePath)
    ?.readText()
    ?: throw IllegalArgumentException(
        "Could not find file $filePath. Make sure to put it in the correct resources folder for $caller's runtime."
    )
