package ie.rubberduck.data.repository

import ie.rubberduck.data.remote.client.MovieClient
import ie.rubberduck.data.remote.dto.MovieDetailsResponseDto
import ie.rubberduck.data.remote.dto.MovieDto
import ie.rubberduck.data.remote.dto.MovieGenresDto
import ie.rubberduck.data.remote.dto.MovieResponseDto
import ie.rubberduck.data.remote.dto.toDomain
import ie.rubberduck.data.room.dao.MovieDao
import ie.rubberduck.data.room.entities.toEntity
import ie.rubberduck.domain.models.MovieDetailsModel
import ie.rubberduck.domain.models.MovieModel
import io.mockk.clearAllMocks
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
class MovieRepositoryImplTest {

    private lateinit var classUnderTest: MovieRepositoryImpl
    private val client: MovieClient = mockk()
    private val dao: MovieDao = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
        classUnderTest = MovieRepositoryImpl(client = client, movieDao = dao)
    }

    @DisplayName(
        """
            GIVEN client returns popular movies
            WHEN I invoke getPopularMovies
            THEN I expect mapped domain models and cache updated
        """
    )
    @Test
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
        coEvery {
            client.getPopularMovies(1)
        } returns MovieResponseDto(
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
        coVerify { dao.clearAll() }
        coVerify { dao.insertAll(expected.map { it.toEntity() }) }
    }

    @DisplayName(
        """
            GIVEN client fails fetching popular movies
            WHEN I invoke getPopularMovies
            THEN I expect cached movies from dao
        """
    )
    @Test
    fun testGetPopularMoviesFallback() = runTest {
        // GIVEN
        coEvery { client.getPopularMovies(1) } throws RuntimeException("Network error")
        val cached = listOf(
            MovieModel(
                id = 10,
                title = "Cached",
                releaseDate = "2025-01-01",
                posterPath = "/cached.jpg",
                overview = "Cached movie",
                voteAverage = 6.0,
            ),
        )
        coEvery { dao.getAllMovies() } returns cached.map { it.toEntity() }

        // WHEN
        val result = classUnderTest.getPopularMovies(1)

        // THEN
        assertEquals(cached, result)
        coVerify { dao.getAllMovies() }
    }

    @DisplayName(
        """
            GIVEN client returns top rated movies
            WHEN I invoke getTopRatedMovies
            THEN I expect mapped domain models and cache updated
        """
    )
    @Test
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
        coEvery {
            client.getTopRatedMovies(1)
        } returns MovieResponseDto(
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
        coVerify { dao.clearAll() }
        coVerify { dao.insertAll(expected.map { it.toEntity() }) }
    }

    @DisplayName(
        """
            GIVEN client fails fetching top rated movies
            WHEN I invoke getTopRatedMovies
            THEN I expect cached movies from dao
        """
    )
    @Test
    fun testGetTopRatedMoviesFallback() = runTest {
        // GIVEN
        coEvery { client.getTopRatedMovies(1) } throws RuntimeException("Network error")
        val cached =
            listOf(
                MovieModel(
                    id = 20,
                    title = "Cached Top",
                    releaseDate = "2019-01-01",
                    posterPath = "/cached2.jpg",
                    overview = "Top rated",
                    voteAverage = 7.0,
                ),
            )
        coEvery { dao.getAllMovies() } returns cached.map { it.toEntity() }

        // WHEN
        val result = classUnderTest.getTopRatedMovies(1)

        // THEN
        assertEquals(cached, result)
        coVerify { dao.getAllMovies() }
    }

    @DisplayName(
        """
            GIVEN client returns movie details
            WHEN I invoke getMovieDetails
            THEN I expect mapped domain model and cache updated
        """
    )
    @Test
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
            genres = listOf(
                MovieGenresDto(id = 1, name = "Sci-Fi"),
                MovieGenresDto(id = 2, name = "Action")
            )
        )
        coEvery { client.getMovieDetails(42) } returns dto
        val expected = dto.toDomain()

        // WHEN
        val result: MovieDetailsModel = classUnderTest.getMovieDetails(42)

        // THEN
        assertEquals(expected, result)
        coVerify { dao.insertMovieDetails(expected.toEntity()) }
    }

    @DisplayName(
        """
            GIVEN client fails fetching movie details
            WHEN I invoke getMovieDetails
            THEN I expect cached details from dao
        """
    )
    @Test
    fun testGetMovieDetailsFallback() = runTest {
        // GIVEN
        coEvery { client.getMovieDetails(42) } throws RuntimeException("Network error")
        val cached = MovieDetailsModel(
            id = 42,
            title = "Cached Matrix",
            duration = 120,
            releaseDate = "1999-01-01",
            backdropPath = null,
            posterPath = "/poster.jpg",
            overview = "Cached overview",
            voteAverage = 8.0,
            genres = emptyList()
        )
        coEvery { dao.getMovieDetails(42) } returns cached.toEntity()

        // WHEN
        val result = classUnderTest.getMovieDetails(42)

        // THEN
        assertEquals(cached, result)
        coVerify { dao.getMovieDetails(42) }
    }

    @DisplayName(
        """
            GIVEN client returns search results
            WHEN I invoke searchMovies
            THEN I expect mapped domain models and cache updated
        """
    )
    @Test
    fun testSearchMovies() = runTest {
        // GIVEN
        val dto = MovieDto(
            id = 3,
            title = "Interstellar",
            releaseDate = "2014-11-07",
            posterPath = "/poster3.jpg",
            overview = "Exploration",
            voteAverage = 8.6
        )
        coEvery {
            client.searchMovies("Interstellar")
        } returns MovieResponseDto(
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
        coVerify { dao.insertAll(expected.map { it.toEntity() }) }
    }

    @DisplayName(
        """
            GIVEN client fails searching movies
            WHEN I invoke searchMovies
            THEN I expect cached search results from dao
        """
    )
    @Test
    fun testSearchMoviesFallback() = runTest {
        // GIVEN
        coEvery { client.searchMovies("Query") } throws RuntimeException("Network error")
        val cached = listOf(
            MovieModel(
                id = 99,
                title = "Cached Search",
                releaseDate = "2021-01-01",
                posterPath = null,
                overview = "Cached",
                voteAverage = 5.5,
            ),
        )
        coEvery { dao.searchMoviesByTitle("Query") } returns cached.map { it.toEntity() }

        // WHEN
        val result = classUnderTest.searchMovies("Query")

        // THEN
        assertEquals(cached, result)
        coVerify { dao.searchMoviesByTitle("Query") }
    }
}