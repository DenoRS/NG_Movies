package ie.rubberduck.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ie.rubberduck.domain.models.MovieModel

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val overview: String,
    val voteAverage: Double
)

fun MovieEntity.toDomain() = MovieModel(
    id = id,
    title = title,
    releaseDate = releaseDate,
    posterPath = posterPath,
    overview = overview,
    voteAverage = voteAverage
)

fun MovieModel.toEntity() = MovieEntity(
    id = id,
    title = title,
    releaseDate = releaseDate,
    posterPath = posterPath,
    overview = overview,
    voteAverage = voteAverage
)