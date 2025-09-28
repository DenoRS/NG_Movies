package ie.rubberduck.ngmovies.features.search

import ie.rubberduck.ngmovies.features.dashboard.MovieUiModel

data class SearchViewState(
    val uiState: SearchUiState = SearchUiState.Idle,
    val query: String = ""
)

sealed class SearchUiState {
    object Idle : SearchUiState()
    object Loading : SearchUiState()
    data class Content(val movies: List<MovieUiModel>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}