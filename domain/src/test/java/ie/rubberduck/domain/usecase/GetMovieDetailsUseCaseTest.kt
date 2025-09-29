package ie.rubberduck.domain.usecase

import ie.rubberduck.domain.models.MovieDetailsModel
import ie.rubberduck.domain.models.MovieGenresModel
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

class GetMovieDetailsUseCaseTest {

    private lateinit var classUnderTest: GetMovieDetailsUseCase

    private val repository: MovieRepository = mockk()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @BeforeEach
    fun setUp() {
        classUnderTest = GetMovieDetailsUseCase(repository, testDispatcher)
    }

    @Test
    @DisplayName(
        """
            GIVEN a valid movieId
            WHEN I invoke this use case
            THEN I expect movie details to be returned
        """
    )
    fun testReturnsMovieDetails() = testScope.runTest {
        // GIVEN
        val movieId = 42
        val expectedDetails = MovieDetailsModel(
            id = 42,
            title = "Inception",
            releaseDate = "2010-07-16",
            backdropPath = "/inception-backdrop.jpg",
            posterPath = "/inception.jpg",
            overview = "A thief who steals corporate secrets through dreams.",
            voteAverage = 8.8,
            duration = 148,
            genres = listOf(
                MovieGenresModel(
                    id = 1,
                    name = "Action"
                ),
                MovieGenresModel(
                    id = 2,
                    name = "Sci-Fi"
                ),
                MovieGenresModel(
                    id = 3,
                    name = "Thriller"
                )
            )
        )
        coEvery { repository.getMovieDetails(movieId) } returns expectedDetails

        // WHEN
        val actualDetails = classUnderTest(movieId).first()

        // THEN
        assertEquals(expectedDetails, actualDetails)
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
        val movieId = 99
        val exception = RuntimeException("Network error")
        coEvery { repository.getMovieDetails(movieId) } throws exception

        // WHEN & THEN
        val thrown = assertThrows<RuntimeException> {
            classUnderTest(movieId).first()
        }
        assertEquals("Network error", thrown.message)
    }
}
