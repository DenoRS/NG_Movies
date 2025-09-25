package ie.rubberduck.ngmovies.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.rubberduck.domain.interactor.GetMoviesInteractor
import ie.rubberduck.ngmovies.features.dashboard.MovieUiModelMapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getMoviesInteractor: GetMoviesInteractor,
) : ViewModel() {

    private val _viewState = MutableStateFlow(DashboardViewState())
    val viewState: StateFlow<DashboardViewState> = _viewState.asStateFlow()

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            _viewState.update { it.copy(uiState = DashboardUiState.Loading) }

            try {
                val movies = getMoviesInteractor()
                val mappedMovies = movies.map { it.toUiModel() }
                _viewState.update { it.copy(movies = mappedMovies) }
            } catch (e: Exception) {
                _viewState.update {
                    it.copy(
                        uiState = DashboardUiState.Error(
                            errorMessage = e.message ?: "Something went wrong"
                        )
                    )
                }
            }

        }
    }
}