package ie.rubberduck.domain.usecase

import ie.rubberduck.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    operator fun invoke(movieId: Int) = flow {
        emit(repository.getMovieDetails(movieId))
    }.flowOn(dispatcher)

}