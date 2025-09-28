package ie.rubberduck.data.remote.client

import ie.rubberduck.data.remote.dto.MovieResponseDto

interface MovieClient {

    suspend fun getPopularMovies(page: Int = 1): MovieResponseDto

    suspend fun getTopRatedMovies(page: Int = 1): MovieResponseDto
}