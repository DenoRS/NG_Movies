package ie.rubberduck.data.remote.client

import ie.rubberduck.data.remote.api.MovieApi
import ie.rubberduck.data.remote.dto.MovieDto
import javax.inject.Inject

class MovieClientImpl @Inject constructor(
    private val api: MovieApi,
) : MovieClient {

    override suspend fun getMovies(): List<MovieDto> = api.getMovies()
}