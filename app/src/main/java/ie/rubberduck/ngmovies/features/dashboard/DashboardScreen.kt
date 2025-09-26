package ie.rubberduck.ngmovies.features.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.rubberduck.components.common.LoadingView
import ie.rubberduck.components.toolbar.NGTopAppBar
import ie.rubberduck.ngmovies.R
import ie.rubberduck.ngmovies.features.dashboard.components.MovieCard

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
            title = stringResource(R.string.dashboard_title),
            actionIcon = Icons.Filled.Search,
            actionIconContentDescription = "Search"
        )

        when (vmState.value.uiState) {
            DashboardUiState.Loading -> LoadingScreen()
            DashboardUiState.Content -> ContentScreen(vmState.value.movies)
            is DashboardUiState.Error -> ErrorScreen(vmState.value)
        }

    }
}

@Composable
private fun LoadingScreen() {
    LoadingView()
}

@Composable
private fun ContentScreen(
    movies: List<MovieUiModel>
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "movies")
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
        MovieCard(
            imageUrl = "https://dummyimage.com/250x250/cccccc/000000.png&text=Hello there",
            title = "some movie",
            duration = "2h 30m"
        )
        Text(text = "something went wrong")
    }

}


internal class DashboardScreenPreviewParams : PreviewParameterProvider<DashboardViewState> {
    override val values: Sequence<DashboardViewState>
        get() = sequenceOf(
            DashboardViewState(
                uiState = DashboardUiState.Loading
            ),
            DashboardViewState(
                uiState = DashboardUiState.Content,
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
            DashboardViewState(
                uiState = DashboardUiState.Error("some error")
            )
        )
}