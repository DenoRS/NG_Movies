package ie.rubberduck.ngmovies.features.dashboard

data class DashboardViewState(
    val uiState: DashboardUiState = DashboardUiState.Loading,
    val movies: List<MovieUiModel> = emptyList(),
)

sealed class DashboardUiState {
    data object Loading : DashboardUiState()
    data object Content : DashboardUiState()
    data class Error(val errorMessage: String) : DashboardUiState()
}