package ie.rubberduck.domain.repository

import ie.rubberduck.domain.models.MovieModel

interface MovieRepository {

    suspend fun getMovies(): List<MovieModel>
}