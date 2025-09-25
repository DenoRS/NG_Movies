package ie.rubberduck.data.remote.api

import ie.rubberduck.data.remote.dto.MovieDto
import retrofit2.http.GET

interface MovieApi {

    @GET("")
    suspend fun getMovies(): List<MovieDto>
}