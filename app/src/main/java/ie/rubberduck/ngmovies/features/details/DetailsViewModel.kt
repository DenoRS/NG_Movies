package ie.rubberduck.ngmovies.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.rubberduck.domain.usecase.GetMovieDetailsUseCase
import ie.rubberduck.ngmovies.features.dashboard.DashboardViewState
import ie.rubberduck.ngmovies.features.details.MovieDetailsUiModelMapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(DetailsViewState())
    val viewState: StateFlow<DetailsViewState> = _viewState.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId).collect { movie ->
                _viewState.update {
                    it.copy(
                        uiState = DetailsUiState.Content(
                            movie.toUiModel()
                        ),
                        movieId = movieId
                    )
                }
            }
        }
    }

    internal fun retry() {
        getMovieDetails(_viewState.value.movieId ?: 0)
    }

}