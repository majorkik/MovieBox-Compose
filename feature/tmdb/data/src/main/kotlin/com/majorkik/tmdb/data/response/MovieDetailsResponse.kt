package com.majorkik.tmdb.data.response

import com.majorkik.tmdb.api.model.MovieDetails
import com.majorkik.tmdb.api.model.image.toBackdrop
import com.majorkik.tmdb.api.model.image.toPoster
import com.majorkik.tmdb.api.model.image.toProfile
import com.majorkik.tmdb.data.util.tryParseDateFromAPI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieDetailsResponse(
    @SerialName("adult") val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("belongs_to_collection") val belongsToCollection: BelongsToCollection?,
    @SerialName("budget") val budget: Long,
    @SerialName("genres") val genres: List<Genre>,
    @SerialName("homepage") val homepage: String?,
    @SerialName("id") val id: Long,
    @SerialName("imdb_id") val imdbId: String?,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("overview") val overview: String?,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompany>,
    @SerialName("production_countries") val productionCountries: List<ProductionCountry>,
    @SerialName("release_date") val releaseDate: String?,
    @SerialName("revenue") val revenue: Long,
    @SerialName("runtime") val runtime: Int?,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguage>,
    @SerialName("status") val status: String,
    @SerialName("tagline") val tagline: String?,
    @SerialName("title") val title: String,
    @SerialName("video") val video: Boolean,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("images") val images: Images?,
    @SerialName("credits") val credits: Credits?,
) {
    @Serializable
    data class Genre(@SerialName("id") val id: Long, @SerialName("name") val name: String)

    @Serializable
    data class BelongsToCollection(
        @SerialName("id") val id: Long,
        @SerialName("name") val name: String,
        @SerialName("poster_path") val posterPath: String?,
        @SerialName("backdrop_path") val backdropPath: String?,
    )

    @Serializable
    data class ProductionCompany(
        @SerialName("id") val id: Long,
        @SerialName("logo_path") val logoPath: String?,
        @SerialName("name") val name: String,
        @SerialName("origin_country") val originCountry: String,
    )

    @Serializable
    data class ProductionCountry(
        @SerialName("iso_3166_1") val iso31661: String,
        @SerialName("name") val name: String,
    )

    @Serializable
    data class SpokenLanguage(
        @SerialName("iso_639_1") val iso6391: String,
        @SerialName("name") val name: String,
    )

    @Serializable
    data class Images(
        @SerialName("backdrops") val backdrops: List<Image>,
        @SerialName("posters") val posters: List<Image>,
    ) {
        @Serializable
        data class Image(
            @SerialName("aspect_ratio") val aspectRatio: Double?,
            @SerialName("file_path") val filePath: String?,
        )
    }

    @Serializable
    data class Credits(
        @SerialName("cast") val cast: List<Cast>,
        @SerialName("crew") val crew: List<Crew>,
    ) {
        @Serializable
        data class Cast(
            @SerialName("cast_id") val castId: Int,
            @SerialName("character") val character: String,
            @SerialName("credit_id") val creditId: String,
            @SerialName("id") val id: Int,
            @SerialName("known_for_department") val knownForDepartment: String,
            @SerialName("name") val name: String,
            @SerialName("order") val order: Int,
            @SerialName("original_name") val originalName: String,
            @SerialName("popularity") val popularity: Double,
            @SerialName("profile_path") val profilePath: String?,
        )

        @Serializable
        data class Crew(
            @SerialName("credit_id") val creditId: String,
            @SerialName("department") val department: String,
            @SerialName("id") val id: Int,
            @SerialName("job") val job: String,
            @SerialName("known_for_department") val knownForDepartment: String,
            @SerialName("name") val name: String,
            @SerialName("original_name") val originalName: String,
            @SerialName("popularity") val popularity: Double,
            @SerialName("profile_path") val profilePath: String?,
        )
    }
}

internal fun MovieDetailsResponse.toDomainModel() = MovieDetails(
    adult = adult,
    backdrop = backdropPath,
    belongsToCollection = belongsToCollection?.toDomainModel(),
    budget = budget,
    genres = genres.map { it.toDomainModel() },
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    poster = posterPath,
    productionCompanies = productionCompanies.map { it.toDomainModel() },
    productionCountries = productionCountries.map { it.toDomainModel() },
    releaseDate = releaseDate?.let(::tryParseDateFromAPI),
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages.map { it.toDomainModel() },
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    posters = images?.posters?.mapNotNull { it.filePath?.toPoster() }.orEmpty(),
    backdrops = images?.backdrops?.mapNotNull { it.filePath?.toBackdrop() }.orEmpty(),
    casts = credits?.cast?.map { it.toDomainModel() }.orEmpty(),
    crews = credits?.crew?.map { it.toDomainModel() }.orEmpty(),
)

internal fun MovieDetailsResponse.Genre.toDomainModel() = MovieDetails.Genre(id = id, name = name)

internal fun MovieDetailsResponse.BelongsToCollection.toDomainModel() = MovieDetails.BelongsToCollection(
    id = id,
    name = name,
    poster = posterPath,
    backdrop = backdropPath,
)

internal fun MovieDetailsResponse.ProductionCompany.toDomainModel() = MovieDetails.ProductionCompany(
    id = id,
    logo = logoPath,
    name = name,
    originCountry = originCountry,
)

internal fun MovieDetailsResponse.ProductionCountry.toDomainModel() =
    MovieDetails.ProductionCountry(iso = iso31661, name = name)

internal fun MovieDetailsResponse.SpokenLanguage.toDomainModel() =
    MovieDetails.SpokenLanguage(iso = iso6391, name = name)

internal fun MovieDetailsResponse.Credits.Cast.toDomainModel() = MovieDetails.Cast(
    id = id,
    castId = castId,
    character = character,
    creditId = creditId,
    knownForDepartment = knownForDepartment,
    name = name,
    originalName = originalName,
    order = order,
    popularity = popularity,
    profile = profilePath?.toProfile(),
)

internal fun MovieDetailsResponse.Credits.Crew.toDomainModel() = MovieDetails.Crew(
    id = id,
    creditId = creditId,
    department = department,
    job = job,
    knownForDepartment = knownForDepartment,
    name = name,
    originalName = originalName,
    popularity = popularity,
    profile = profilePath?.toProfile(),
)
