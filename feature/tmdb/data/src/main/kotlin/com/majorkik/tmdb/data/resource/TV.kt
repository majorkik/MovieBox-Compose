package com.majorkik.tmdb.data.resource

import io.ktor.resources.Resource

@Resource("/tv")
internal class TV {

    @Resource("popular")
    class Popular(val parent: TV = TV())

    @Resource("{id}")
    class Id(val parent: TV = TV(), val id: Int)
}
