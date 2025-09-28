package ie.rubberduck.ngmovies.features.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ie.rubberduck.components.common.LoadingView
import ie.rubberduck.components.common.UiErrorView
import ie.rubberduck.components.toolbar.NGSearchAppBar
import ie.rubberduck.data.remote.api.ApiConfig
import ie.rubberduck.ngmovies.R
import ie.rubberduck.ngmovies.ext.roundTo1Decimal
import ie.rubberduck.ngmovies.features.dashboard.MovieUiModel
import ie.rubberduck.ngmovies.features.search.components.MovieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit = {},
) {
    val vmState = viewModel.viewState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        NGSearchAppBar(
            navigationIconContentDescription = stringResource(R.string.content_desc_arrow_back),
            startInSearchMode = true,
            onNavigationClick = onBackClick,
            onSearchTriggered = { query -> viewModel.searchMovies(query) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (val uiState = vmState.value.uiState) {
            SearchUiState.Idle -> IdleSearchView()

            SearchUiState.Loading -> LoadingView()
            is SearchUiState.Content -> {
                if (uiState.movies.isEmpty()) {
                    NoResultsView()
                } else {
                    SearchContent(
                        movies = uiState.movies,
                        onMovieClick = onMovieClick
                    )
                }
            }

            is SearchUiState.Error -> UiErrorView(
                message = uiState.message,
                onRetry = { viewModel.searchMovies("123") }
            )
        }
    }
}

@Composable
private fun SearchContent(
    movies: List<MovieUiModel>,
    onMovieClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(movies) { index, movie ->
            MovieItem(
                imageUrl = ApiConfig.IMAGE_BASE_URL_ORIGINAL + movie.posterPath,
                title = movie.title,
                rating = "${movie.voteAverage.roundTo1Decimal()}",
                onClick = { onMovieClick(movie.id) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (index != movies.lastIndex) {
                HorizontalDivider(thickness = 2.dp)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}

@Composable
private fun IdleSearchView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.content_desc_search),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Type a Movie title to search",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun NoResultsView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.ic_cancel),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.search_no_results_found_title),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.search_no_results_found_body),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}