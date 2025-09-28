package ie.rubberduck.ngmovies.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.rubberduck.domain.usecase.GetPopularMoviesUseCase
import ie.rubberduck.domain.usecase.GetTopRatedMoviesUseCase
import ie.rubberduck.ngmovies.features.dashboard.MovieUiModelMapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(DashboardViewState())
    val viewState: StateFlow<DashboardViewState> = _viewState.asStateFlow()

    init {
        getMovies()
    }

    internal fun getMovies() {
        getPopularMovies()
        getTopRatedMovies()
    }

    internal fun retry() {
        getMovies()
    }

    private fun getPopularMovies(page: Int = 1) {
        viewModelScope.launch {
            getPopularMoviesUseCase(page).collect { movies ->
                _viewState.update {
                    it.copy(
                        popularUiState = MoviesUiState.Content(
                            movies.map { movie ->
                                movie.toUiModel()
                            }
                        )
                    )
                }
            }
        }
    }

    private fun getTopRatedMovies(page: Int = 1) {
        viewModelScope.launch {
            getTopRatedMoviesUseCase(page).collect { movies ->
                _viewState.update {
                    it.copy(
                        topRatedUiState = MoviesUiState.Content(
                            movies.map { movie ->
                                movie.toUiModel()
                            }
                        )
                    )
                }
            }
        }
    }

}