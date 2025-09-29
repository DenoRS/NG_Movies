package ie.rubberduck.domain.usecase

import ie.rubberduck.domain.models.MovieModel
import ie.rubberduck.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetTopRatedMoviesUseCaseTest {

    private lateinit var classUnderTest: GetTopRatedMoviesUseCase

    private val repository: MovieRepository = mockk()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @BeforeEach
    fun setUp() {
        classUnderTest = GetTopRatedMoviesUseCase(repository, testDispatcher)
    }

    @Test
    @DisplayName(
        """
            GIVEN a valid page number
            WHEN I invoke this use case
            THEN I expect a list of top rated movies to be returned
        """
    )
    fun testReturnsTopRatedMovies() = testScope.runTest {
        // GIVEN
        val page = 1
        val expectedMovies = listOf(
            MovieModel(
                id = 101,
                title = "The Dark Knight",
                releaseDate = "2008-07-18",
                posterPath = "/batman.jpg",
                overview = "Batman vs Joker",
                voteAverage = 9.0
            )
        )
        coEvery { repository.getTopRatedMovies(page) } returns expectedMovies

        // WHEN
        val actualMovies = classUnderTest(page).first()

        // THEN
        assertEquals(expectedMovies, actualMovies)
    }

    @Test
    @DisplayName(
        """
            GIVEN a valid page number but no results
            WHEN I invoke this use case
            THEN I expect an empty list to be returned
        """
    )
    fun testReturnsEmptyList() = testScope.runTest {
        // GIVEN
        val page = 2
        coEvery { repository.getTopRatedMovies(page) } returns emptyList()

        // WHEN
        val actualMovies = classUnderTest(page).first()

        // THEN
        assertEquals(emptyList<MovieModel>(), actualMovies)
    }

    @Test
    @DisplayName(
        """
            GIVEN repository throws exception
            WHEN I invoke this use case
            THEN I expect a RuntimeException to be propagated
        """
    )
    fun testThrowsException() = testScope.runTest {
        // GIVEN
        val page = 3
        val exception = RuntimeException("Network error")
        coEvery { repository.getTopRatedMovies(page) } throws exception

        // WHEN & THEN
        val thrown = assertThrows<RuntimeException> {
            classUnderTest(page).first()
        }
        assertEquals("Network error", thrown.message)
    }
}
