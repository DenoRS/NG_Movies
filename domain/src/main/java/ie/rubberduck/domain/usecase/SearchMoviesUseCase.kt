package ie.rubberduck.domain.usecase

import ie.rubberduck.domain.models.MovieModel
import ie.rubberduck.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    operator fun invoke(query: String): Flow<List<MovieModel>> = flow {
        emit(repository.searchMovies(query))
    }.flowOn(dispatcher)
}