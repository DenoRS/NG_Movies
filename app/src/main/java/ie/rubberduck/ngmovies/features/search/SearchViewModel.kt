package ie.rubberduck.ngmovies.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.rubberduck.domain.usecase.SearchMoviesUseCase
import ie.rubberduck.ngmovies.features.mappers.MovieUiModelMapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(SearchViewState())
    val viewState: StateFlow<SearchViewState> = _viewState

//    fun onQueryChanged(query: String) {
//        _viewState.update { it.copy(query = query) }
//    }

    fun searchMovies(query: String) {
        if (query.isBlank()) return
        viewModelScope.launch {
            _viewState.update { it.copy(uiState = SearchUiState.Loading) }
            try {
                searchMoviesUseCase(query).collect { movies ->
                    _viewState.update {
                        it.copy(
                            uiState = SearchUiState.Content(
                                movies.map { movie ->
                                    movie.toUiModel()
                                }
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                _viewState.update {
                    it.copy(uiState = SearchUiState.Error(e.message ?: "Unknown error"))
                }
            }
        }
    }
}