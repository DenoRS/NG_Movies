package ie.rubberduck.ngmovies.features.details

data class DetailsViewState(
    val uiState: DetailsUiState = DetailsUiState.Loading,
    val movieId: Int? = null,
)

sealed class DetailsUiState {
    data object Loading : DetailsUiState()
    data class Content(val movie: MovieDetailsUiModel) : DetailsUiState()
    data class Error(val errorMessage: String) : DetailsUiState()
}