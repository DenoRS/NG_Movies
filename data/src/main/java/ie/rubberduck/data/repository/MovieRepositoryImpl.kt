package ie.rubberduck.data.repository

import ie.rubberduck.data.remote.client.MovieClient
import ie.rubberduck.domain.models.MovieModel
import ie.rubberduck.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val client: MovieClient,
) : MovieRepository {

    override suspend fun getMovies(): List<MovieModel> {
        client.getMovies()
        return emptyList()
//        TODO("Not yet implemented")
    }
}