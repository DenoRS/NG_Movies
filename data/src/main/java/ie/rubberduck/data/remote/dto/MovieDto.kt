package ie.rubberduck.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ie.rubberduck.domain.models.MovieModel

@JsonClass(generateAdapter = true)
data class MovieDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "vote_average") val voteAverage: Double
)

fun MovieDto.toDomain() = MovieModel(
    id = id,
    title = title,
    releaseDate = releaseDate,
    posterPath = posterPath,
    overview = overview,
    voteAverage = voteAverage
)