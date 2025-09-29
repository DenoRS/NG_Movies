package ie.rubberduck.domain.usecase

import ie.rubberduck.domain.models.MovieModel
import ie.rubberduck.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@OptIn(ExperimentalCoroutinesApi::class)
class SearchMoviesUseCaseTest {
    private lateinit var classUnderTest: SearchMoviesUseCase

    private val repository: MovieRepository = mockk()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @BeforeEach
    fun setUp() {
        classUnderTest = SearchMoviesUseCase(repository, testDispatcher)
    }

    @Test
    @DisplayName(
        """
            GIVEN a valid query
            WHEN I invoke this use case
            THEN I expect a list of movies to be returned
        """
    )
    fun testReturnsMovies() = testScope.runTest {
        // GIVEN
        val query = "Fast"
        val expectedMovies = listOf(
            MovieModel(
                id = 1,
                title = "Fast & Furious",
                releaseDate = "2001-06-22",
                posterPath = "/poster1.jpg",
                overview = "Action movie",
                voteAverage = 7.5
            )
        )
        coEvery { repository.searchMovies(query) } returns expectedMovies

        // WHEN
        val actualMovies = classUnderTest(query).first()

        // THEN
        assertEquals(expectedMovies, actualMovies)
    }

    @Test
    @DisplayName(
        """
            GIVEN an unknown query
            WHEN I invoke this use case
            THEN I expect an empty list to be returned
        """
    )
    fun testReturnsEmptyList() = testScope.runTest {
        // GIVEN
        val query = "Unknown"
        coEvery { repository.searchMovies(query) } returns emptyList()

        // WHEN
        val actualMovies = classUnderTest(query).first()

        // THEN
        assertEquals(emptyList<MovieModel>(), actualMovies)
    }

    @Test
    @DisplayName(
        """
            GIVEN repository throws exception
            WHEN I invoke this use case
            THEN I expect an RuntimeException to be propagated"
        """
    )
    fun testThrowsException() = testScope.runTest {
        // GIVEN
        val query = "invalidQuery"
        val exception = RuntimeException("Network error")
        coEvery { repository.searchMovies(query) } throws exception

        // WHEN & THEN
        val thrown = assertThrows<RuntimeException> {
            classUnderTest(query).first()
        }
        assertEquals("Network error", thrown.message)
    }
}