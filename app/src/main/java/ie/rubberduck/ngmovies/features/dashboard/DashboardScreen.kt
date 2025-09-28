package ie.rubberduck.ngmovies.features.dashboard

import NGTitleLargePrimary
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.rubberduck.components.common.LoadingRow
import ie.rubberduck.components.toolbar.NGTopAppBar
import ie.rubberduck.ngmovies.R
import ie.rubberduck.ngmovies.features.dashboard.components.MovieCard
import ie.rubberduck.ngmovies.features.dashboard.components.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val vmState = viewModel.viewState.collectAsState()
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
            MoviesContent(vmState.value.popularUiState)

            Spacer(modifier = Modifier.height(16.dp))

            // Top rated movies row
            NGTitleLargePrimary(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.dashboard_title_top_rated)
            )
            Spacer(modifier = Modifier.height(16.dp))
            MoviesContent(vmState.value.topRatedUiState)
        }

//        when (vmState.value.uiState) {
//            DashboardUiState.Loading -> LoadingScreen()
//            is DashboardUiState.Content -> ContentScreen(vmState.value.movies)
//            is DashboardUiState.Error -> ErrorScreen(vmState.value)
//        }

    }
}

@Composable
private fun ErrorScreen(
    vmState: DashboardViewState,
//    errorMessage: String
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        MovieCard(
//            imageUrl = "https://dummyimage.com/250x250/cccccc/000000.png&text=Hello there",
//            title = "some movie",
//            rating = "2h 30m"
//        )
        Text(text = "something went wrong")
    }
}

@Composable
fun MoviesContent(
    uiState: MoviesUiState
) {
    when (uiState) {
        MoviesUiState.Loading -> LoadingRow()

        is MoviesUiState.Content -> {
            MovieRow(uiState.movies)
        }

        is MoviesUiState.Error -> {
            Text(text = uiState.errorMessage)
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
                            title = "Movie 1",
                            releaseDate = "25-09-2025",
                            posterPath = "",
                            overview = "",
                            voteAverage = 4.5
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