package com.majorkik.tmdb.impl.response

import com.majorkik.tmdb.api.model.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GenresResponse(
    @SerialName("genres") val genres: List<Genre>
) {
    @Serializable
    data class Genre(
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String
    )
}

internal fun GenresResponse.toDomainModel(): List<Genre> {
    return genres.map(GenresResponse.Genre::toDomainModel)
}

internal fun GenresResponse.Genre.toDomainModel(): Genre = Genre(id = id, name = name)
