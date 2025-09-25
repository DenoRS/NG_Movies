package ie.rubberduck.ngmovies.features.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.rubberduck.components.common.LoadingView

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val vmState = viewModel.viewState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
        Text(text = "something went wrong")
    }

}


@Preview(showBackground = true)
@Composable
private fun DashboardScreenPreview() {

}