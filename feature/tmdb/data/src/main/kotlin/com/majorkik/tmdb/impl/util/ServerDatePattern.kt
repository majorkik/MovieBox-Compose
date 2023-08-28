package com.majorkik.tmdb.impl.util

import com.majorkik.common.tryParseDate
import com.soywiz.klock.Date

internal enum class ServerDatePattern(val pattern: String) {
    DEFAULT_SERVER_PATTERN("yyyy-mm-dd")
}

internal fun tryParseDateFromAPI(date: String): Date? {
    return tryParseDate(date = date, pattern = ServerDatePattern.DEFAULT_SERVER_PATTERN.pattern)
}
