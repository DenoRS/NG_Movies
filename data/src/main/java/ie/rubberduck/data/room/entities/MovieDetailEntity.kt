package ie.rubberduck.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ie.rubberduck.domain.models.MovieDetailsModel

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val duration: Int,
    val releaseDate: String,
    val backdropPath: String?,
    val posterPath: String?,
    val overview: String,
    val voteAverage: Double,
    val genres: List<MovieGenresEntity>,
)

fun MovieDetailsEntity.toDomain() = MovieDetailsModel(
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

fun MovieDetailsModel.toEntity() = MovieDetailsEntity(
    id = id,
    title = title,
    duration = duration,
    releaseDate = releaseDate,
    backdropPath = backdropPath,
    posterPath = posterPath,
    overview = overview,
    voteAverage = voteAverage,
    genres = genres.map { it.toEntity() }
)