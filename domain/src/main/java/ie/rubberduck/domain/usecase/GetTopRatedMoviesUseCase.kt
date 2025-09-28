package ie.rubberduck.domain.usecase

import ie.rubberduck.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    operator fun invoke(page: Int) = flow {
        emit(repository.getTopRatedMovies(page))
    }.flowOn(dispatcher)
}