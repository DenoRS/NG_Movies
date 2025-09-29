package ie.rubberduck.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ie.rubberduck.data.room.entities.MovieDetailsEntity
import ie.rubberduck.data.room.entities.MovieEntity

@Dao
interface MovieDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    suspend fun searchMoviesByTitle(query: String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movie: MovieDetailsEntity)

    @Query("SELECT * FROM movie_details WHERE id = :movieId")
    suspend fun getMovieDetails(movieId: Int): MovieDetailsEntity?

    @Query("DELETE FROM movies")
    suspend fun clearAll()

}