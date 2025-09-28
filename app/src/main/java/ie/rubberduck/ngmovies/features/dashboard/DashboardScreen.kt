package ie.rubberduck.ngmovies.features.dashboard

import NGTitleLargePrimary
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ie.rubberduck.components.common.LoadingRow
import ie.rubberduck.components.common.UiErrorView
import ie.rubberduck.components.toolbar.NGTopAppBar
import ie.rubberduck.ngmovies.R
import ie.rubberduck.ngmovies.features.dashboard.components.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit,
) {
    val vmState = viewModel.viewState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NGTopAppBar(
            title = stringResource(R.string.dashboard_appbar_title),
            actionIcon = Icons.Filled.Search,
            actionIconContentDescription = stringResource(R.string.content_desc_search)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            // Most popular movies row
            NGTitleLargePrimary(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.dashboard_title_most_popular)
            )
            Spacer(modifier = Modifier.height(16.dp))
            MoviesContent(
                uiState = vmState.value.popularUiState,
                onMovieClick = onMovieClick,
                onRetryClick = { viewModel.retry() },
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Top rated movies row
            NGTitleLargePrimary(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.dashboard_title_top_rated)
            )
            Spacer(modifier = Modifier.height(16.dp))
            MoviesContent(
                uiState = vmState.value.topRatedUiState,
                onMovieClick = onMovieClick,
                onRetryClick = { viewModel.retry() }
            )
        }
    }
}

@Composable
fun MoviesContent(
    uiState: MoviesUiState,
    onMovieClick: (Int) -> Unit,
    onRetryClick: () -> Unit,
) {
    when (uiState) {
        MoviesUiState.Loading -> LoadingRow()

        is MoviesUiState.Content -> {
            MovieRow(
                movies = uiState.movies,
                onMovieClick = onMovieClick,
            )
        }

        is MoviesUiState.Error -> {
            UiErrorView(
                message = uiState.errorMessage,
                onRetry = onRetryClick,
            )
        }
    }
}

internal class DashboardScreenPreviewParams : PreviewParameterProvider<DashboardViewState> {
    override val values: Sequence<DashboardViewState>
        get() = sequenceOf(
            DashboardViewState(
                popularUiState = MoviesUiState.Loading,
                topRatedUiState = MoviesUiState.Loading,
            ),
            DashboardViewState(
                popularUiState = MoviesUiState.Content(
                    movies = listOf(
                        MovieUiModel(
                            id = 1,
                            title = "Movie 1",
                            releaseDate = "25-09-2025",
                            posterPath = "",
                            overview = "",
                            voteAverage = 4.5
                        )
                    )
                ),
                topRatedUiState = MoviesUiState.Content(
                    movies = listOf(
                        MovieUiModel(
                            id = 2,
                            title = "Movie 2",
                            releaseDate = "21-09-2025",
                            posterPath = "",
                            overview = "",
                            voteAverage = 4.0
                        )
                    )
                ),
            ),
            DashboardViewState(
                popularUiState = MoviesUiState.Error("some error"),
                topRatedUiState = MoviesUiState.Error("some error")
            )
        )
}