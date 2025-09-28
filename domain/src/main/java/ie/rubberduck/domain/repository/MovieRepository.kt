package ie.rubberduck.domain.repository

import ie.rubberduck.domain.models.MovieDetailsModel
import ie.rubberduck.domain.models.MovieModel

interface MovieRepository {

    suspend fun getPopularMovies(page: Int) : List<MovieModel>
    suspend fun getTopRatedMovies(page: Int) : List<MovieModel>

    suspend fun getMovieDetails(movieId: Int) : MovieDetailsModel
}