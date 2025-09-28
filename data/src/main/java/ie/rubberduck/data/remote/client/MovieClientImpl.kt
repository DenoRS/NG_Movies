package ie.rubberduck.data.remote.client

import ie.rubberduck.data.remote.api.MovieApi
import ie.rubberduck.data.remote.dto.MovieDetailsResponseDto
import ie.rubberduck.data.remote.dto.MovieDto
import ie.rubberduck.data.remote.dto.MovieResponseDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieClientImpl @Inject constructor(
    private val api: MovieApi,
) : MovieClient {

    override suspend fun getPopularMovies(page: Int): MovieResponseDto =
        api.getPopularMovies(page)

    override suspend fun getTopRatedMovies(page: Int): MovieResponseDto =
        api.getTopRatedMovies(page)

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsResponseDto =
        api.getMovieDetails(movieId)

    override suspend fun searchMovies(query: String): MovieResponseDto =
        api.searchMovies(query)

}