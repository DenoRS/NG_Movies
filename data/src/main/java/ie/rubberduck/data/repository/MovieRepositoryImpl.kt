package ie.rubberduck.data.repository

import ie.rubberduck.data.remote.client.MovieClient
import ie.rubberduck.data.remote.dto.toDomain
import ie.rubberduck.data.room.dao.MovieDao
import ie.rubberduck.data.room.entities.toDomain
import ie.rubberduck.data.room.entities.toEntity
import ie.rubberduck.domain.models.MovieDetailsModel
import ie.rubberduck.domain.models.MovieModel
import ie.rubberduck.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val client: MovieClient,
    private val movieDao: MovieDao,
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<MovieModel> {
        return try {
            val response = client.getPopularMovies(page)
            val movies = response.results.map { it.toDomain() }
            movieDao.clearAll()
            movieDao.insertAll(movies.map { it.toEntity() })
            movies
        } catch (e: Exception) {
            movieDao.getAllMovies().map { it.toDomain() }
        }
    }

    override suspend fun getTopRatedMovies(page: Int): List<MovieModel> {
        return try {
            val response = client.getTopRatedMovies(page)
            val movies = response.results.map { it.toDomain() }

            movieDao.clearAll()
            movieDao.insertAll(movies.map { it.toEntity() })

            movies
        } catch (e: Exception) {
            movieDao.getAllMovies().map { it.toDomain() }
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsModel {
        return try {
            val details = client.getMovieDetails(movieId).toDomain()
            movieDao.insertMovieDetails(details.toEntity())
            details
        } catch (e: Exception) {
            movieDao.getMovieDetails(movieId)?.toDomain() ?: throw e
        }
    }

    override suspend fun searchMovies(query: String): List<MovieModel> {
        return try {
            val results = client.searchMovies(query).results.map { it.toDomain() }
            movieDao.insertAll(results.map { it.toEntity() })
            results
        } catch (e: Exception) {
            movieDao.searchMoviesByTitle(query).map { it.toDomain() }
        }
    }
}
