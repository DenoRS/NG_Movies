package ie.rubberduck.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import ie.rubberduck.data.room.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieEntity>

}