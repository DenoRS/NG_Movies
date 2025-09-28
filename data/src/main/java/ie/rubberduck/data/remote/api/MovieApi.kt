package ie.rubberduck.data.remote.api

import ie.rubberduck.data.remote.dto.MovieDto
import ie.rubberduck.data.remote.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ): MovieResponseDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ): MovieResponseDto
}