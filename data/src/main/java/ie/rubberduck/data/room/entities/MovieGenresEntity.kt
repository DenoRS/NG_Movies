package ie.rubberduck.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ie.rubberduck.domain.models.MovieGenresModel

@Entity(tableName = "movie_genres")
data class MovieGenresEntity(
    @PrimaryKey val id: Int,
    val name: String
)

fun MovieGenresEntity.toDomain() = MovieGenresModel(
    id = id,
    name = name
)

fun MovieGenresModel.toEntity() = MovieGenresEntity(
    id = id,
    name = name,
)