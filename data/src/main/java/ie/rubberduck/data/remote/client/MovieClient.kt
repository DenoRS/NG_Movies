package ie.rubberduck.data.remote.client

import ie.rubberduck.data.remote.dto.MovieDto

interface MovieClient {

    suspend fun getMovies(): List<MovieDto>
}