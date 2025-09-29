package ie.rubberduck.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ie.rubberduck.domain.models.MovieDetailsModel
import ie.rubberduck.domain.models.MovieGenresModel

@JsonClass(generateAdapter = true)
data class MovieDetailsResponseDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "runtime") val duration: Int,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "overview") val overview: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "genres") val genres: List<MovieGenresDto>
)

@JsonClass(generateAdapter = true)
data class MovieGenresDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)

fun MovieDetailsResponseDto.toDomain() = MovieDetailsModel(
    id = id,
    title = title,
    duration = duration,
    releaseDate = releaseDate,
    backdropPath = backdropPath,
    posterPath = posterPath,
    overview = overview,
    voteAverage = voteAverage,
    genres = genres.map { it.toDomain() }
)

fun MovieGenresDto.toDomain() = MovieGenresModel(
    id = id,
    name = name
)
