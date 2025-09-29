package ie.rubberduck.data.repository

import ie.rubberduck.data.remote.client.MovieClient
import ie.rubberduck.data.remote.dto.MovieDetailsResponseDto
import ie.rubberduck.data.remote.dto.MovieDto
import ie.rubberduck.data.remote.dto.MovieGenresDto
import ie.rubberduck.data.remote.dto.MovieResponseDto
import ie.rubberduck.data.remote.dto.toDomain
import ie.rubberduck.domain.models.MovieDetailsModel
import ie.rubberduck.domain.models.MovieModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@OptIn(ExperimentalCoroutinesApi::class)
class MovieRepositoryImplTest {

    private lateinit var classUnderTest: MovieRepositoryImpl
    private val client: MovieClient = mockk()

    @BeforeEach
    fun setUp() {
        classUnderTest = MovieRepositoryImpl(client)
    }

    @Test
    @DisplayName(
        """
            GIVEN client returns popular movies
            WHEN I call getPopularMovies
            THEN I expect mapped domain models
        """
    )
    fun testGetPopularMovies() = runTest {
        // GIVEN
        val dto = MovieDto(
            id = 1,
            title = "Fast & Furious",
            releaseDate = "2001-06-22",
            posterPath = "/poster1.jpg",
            overview = "Action movie",
            voteAverage = 7.5
        )
        coEvery { client.getPopularMovies(1) } returns MovieResponseDto(
            page = 1,
            results = listOf(dto),
            totalPages = 1,
            totalResults = 1
        )

        val expected = listOf(dto.toDomain())

        // WHEN
        val result = classUnderTest.getPopularMovies(1)

        // THEN
        assertEquals(expected, result)
    }

    @Test
    @DisplayName(
        """
            GIVEN client returns top rated movies
            WHEN I call getTopRatedMovies
            THEN I expect mapped domain models
        """
    )
    fun testGetTopRatedMovies() = runTest {
        // GIVEN
        val dto = MovieDto(
            id = 2,
            title = "Inception",
            releaseDate = "2010-07-16",
            posterPath = "/poster2.jpg",
            overview = "Sci-Fi action",
            voteAverage = 8.8
        )
        coEvery { client.getTopRatedMovies(1) } returns MovieResponseDto(
            page = 1,
            results = listOf(dto),
            totalPages = 1,
            totalResults = 1
        )

        val expected = listOf(dto.toDomain())

        // WHEN
        val result = classUnderTest.getTopRatedMovies(1)

        // THEN
        assertEquals(expected, result)
    }

    @Test
    @DisplayName(
        """
            GIVEN client returns movie details
            WHEN I call getMovieDetails
            THEN I expect mapped domain model
        """
    )
    fun testGetMovieDetails() = runTest {
        // GIVEN
        val dto = MovieDetailsResponseDto(
            id = 42,
            title = "The Matrix",
            duration = 136,
            releaseDate = "1999-03-31",
            backdropPath = "/backdrop.jpg",
            posterPath = "/poster.jpg",
            overview = "Welcome to the real world.",
            voteAverage = 8.7,
            genres = listOf(MovieGenresDto(1, "Sci-Fi"), MovieGenresDto(2, "Action"))
        )
        coEvery { client.getMovieDetails(42) } returns dto

        val expected = dto.toDomain()

        // WHEN
        val result: MovieDetailsModel = classUnderTest.getMovieDetails(42)

        // THEN
        assertEquals(expected, result)
    }

    @Test
    @DisplayName(
        """
            GIVEN client returns search results
            WHEN I call searchMovies
            THEN I expect mapped domain models
        """
    )
    fun testSearchMovies() = runTest {
        // GIVEN
        val dto = MovieDto(
            id = 3,
            title = "Interstellar",
            releaseDate = "2014-11-07",
            posterPath = "/poster3.jpg",
            overview = "Exploration beyond our galaxy",
            voteAverage = 8.6
        )
        coEvery { client.searchMovies("Interstellar") } returns MovieResponseDto(
            page = 1,
            results = listOf(dto),
            totalPages = 1,
            totalResults = 1
        )

        val expected = listOf(dto.toDomain())

        // WHEN
        val result: List<MovieModel> = classUnderTest.searchMovies("Interstellar")

        // THEN
        assertEquals(expected, result)
    }

    @Test
    @DisplayName(
        """
            GIVEN client throws exception
            WHEN I call getPopularMovies
            THEN I expect the exception to propagate
        """
    )
    fun testPropagatesException() = runTest {
        // GIVEN
        coEvery { client.getPopularMovies(1) } throws RuntimeException("Network error")

        // WHEN & THEN
        val thrown = assertThrows<RuntimeException> {
            classUnderTest.getPopularMovies(1)
        }
        assertEquals("Network error", thrown.message)
    }
}