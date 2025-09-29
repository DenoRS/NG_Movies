import ie.rubberduck.data.remote.api.MovieApi
import ie.rubberduck.data.remote.client.MovieClientImpl
import ie.rubberduck.data.remote.dto.MovieDetailsResponseDto
import ie.rubberduck.data.remote.dto.MovieDto
import ie.rubberduck.data.remote.dto.MovieResponseDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieClientImplTest {

    private lateinit var api: MovieApi
    private lateinit var client: MovieClientImpl

    @BeforeEach
    fun setup() {
        api = mockk(relaxed = true)
        client = MovieClientImpl(api)
    }

    @Test
    @DisplayName(
        """
            GIVEN API returns popular movies
            WHEN getPopularMovies is called
            THEN I expect the same response to be returned
        """
    )
    fun testGetPopularMovies() = runTest {
        // GIVEN
        val expected = MovieResponseDto(
            page = 1,
            results = listOf(
                MovieDto(
                    id = 1,
                    title = "Title",
                    releaseDate = "2025-01-01",
                    posterPath = "poster.png",
                    overview = "overview",
                    voteAverage = 7.5,
                ),
            ),
            totalPages = 1,
            totalResults = 1
        )
        coEvery { api.getPopularMovies(1) } returns expected

        // WHEN
        val result = client.getPopularMovies(1)

        // THEN
        assertEquals(expected, result)
        coVerify(exactly = 1) { api.getPopularMovies(1) }
    }

    @Test
    @DisplayName(
        """
            GIVEN API returns top rated movies
            WHEN getTopRatedMovies is called
            THEN I expect the same response to be returned
        """
    )
    fun testGetTopRatedMovies() = runTest {
        val expected = MovieResponseDto(
            page = 1,
            results = listOf(
                MovieDto(
                    id = 2,
                    title = "Top",
                    releaseDate = "2025-01-02",
                    posterPath = null,
                    overview = "overview",
                    voteAverage = 8.2,
                ),
            ),
            totalPages = 1,
            totalResults = 1
        )
        coEvery { api.getTopRatedMovies(1) } returns expected

        val result = client.getTopRatedMovies(1)

        assertEquals(expected, result)
        coVerify(exactly = 1) { api.getTopRatedMovies(1) }
    }

    @Test
    @DisplayName(
        """
            GIVEN API returns movie details
            WHEN getMovieDetails is called
            THEN I expect the same response to be returned
        """
    )
    fun testGetMovieDetails() = runTest {
        val expected = MovieDetailsResponseDto(
            id = 42,
            title = "Details",
            duration = 120,
            releaseDate = "2025-01-03",
            backdropPath = "backdrop.png",
            posterPath = "poster.png",
            overview = "overview",
            voteAverage = 9.0,
            genres = emptyList()
        )
        coEvery { api.getMovieDetails(42) } returns expected

        val result = client.getMovieDetails(42)

        assertEquals(expected, result)
        coVerify(exactly = 1) { api.getMovieDetails(42) }
    }

    @Test
    @DisplayName(
        """
            GIVEN API returns search results
            WHEN searchMovies is called
            THEN I expect the same response to be returned
        """
    )
    fun testSearchMovies() = runTest {
        val expected = MovieResponseDto(
            page = 1,
            results = listOf(
                MovieDto(
                    id = 3,
                    title = "Search",
                    releaseDate = "2025-01-04",
                    posterPath = null,
                    overview = "overview",
                    voteAverage = 6.9,
                ),
            ),
            totalPages = 1,
            totalResults = 1
        )
        coEvery { api.searchMovies("query") } returns expected

        val result = client.searchMovies("query")

        assertEquals(expected, result)
        coVerify(exactly = 1) { api.searchMovies("query") }
    }
}