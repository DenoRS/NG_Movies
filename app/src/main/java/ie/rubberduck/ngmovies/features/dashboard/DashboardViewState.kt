package ie.rubberduck.ngmovies.features.dashboard

data class DashboardViewState(
    val popularUiState: MoviesUiState = MoviesUiState.Loading,
    val topRatedUiState: MoviesUiState = MoviesUiState.Loading,
)


sealed class MoviesUiState {
    data object Loading : MoviesUiState()
    data class Content(val movies: List<MovieUiModel> = emptyList()) : MoviesUiState()
    data class Error(val errorMessage: String) : MoviesUiState()
}

sealed class DashboardUiState {
    data object Loading : DashboardUiState()
    data class Content(val movies: List<MovieUiModel> = emptyList()) : DashboardUiState()
    data class Error(val errorMessage: String) : DashboardUiState()
}