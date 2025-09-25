package ie.rubberduck.domain.interactor

import ie.rubberduck.domain.models.MovieModel
import ie.rubberduck.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesInteractor @Inject constructor(
    private val repository: MovieRepository,
) {

    suspend operator fun invoke(): List<MovieModel> = repository.getMovies()

}