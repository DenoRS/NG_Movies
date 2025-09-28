package ie.rubberduck.data.repository

import ie.rubberduck.data.remote.client.MovieClient
import ie.rubberduck.data.remote.dto.toDomain
import ie.rubberduck.domain.models.MovieDetailsModel
import ie.rubberduck.domain.models.MovieModel
import ie.rubberduck.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val client: MovieClient,
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<MovieModel> =
        client.getPopularMovies(page).results.map { it.toDomain() }

    override suspend fun getTopRatedMovies(page: Int): List<MovieModel> =
        client.getTopRatedMovies(page).results.map { it.toDomain() }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsModel =
        client.getMovieDetails(movieId).toDomain()

}
