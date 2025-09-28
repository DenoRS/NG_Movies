package ie.rubberduck.ngmovies.features.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import ie.rubberduck.components.common.LoadingView
import ie.rubberduck.components.common.UiErrorView
import ie.rubberduck.components.toolbar.NGTopAppBar
import ie.rubberduck.data.remote.api.ApiConfig
import ie.rubberduck.ngmovies.R
import ie.rubberduck.ngmovies.ext.roundTo1Decimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    movieId: Int,
    onBackClick: () -> Unit,
) {
    val vmState = viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        NGTopAppBar(
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onNavigationClick = onBackClick,
            actionIconContentDescription = stringResource(R.string.content_desc_arrow_back)
        )
        Spacer(modifier = Modifier.height(8.dp))

        when (val state = vmState.value.uiState) {
            DetailsUiState.Loading -> LoadingView()
            is DetailsUiState.Content -> DetailsContent(state.movie)
            is DetailsUiState.Error -> {
                UiErrorView(
                    message = state.errorMessage,
                    onRetry = { viewModel.retry() }
                )
            }

        }
    }
}

@Composable
private fun DetailsContent(
    movie: MovieDetailsUiModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            // Backdrop image
            movie.backdropPath?.let { backdrop ->
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    model = ApiConfig.IMAGE_BASE_URL_ORIGINAL + backdrop,
                    contentDescription = "${movie.title} backdrop",
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Title + rating
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${movie.voteAverage.roundTo1Decimal()} ⭐",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Released: ${movie.releaseDate} | ${movie.duration} min",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Poster
            movie.posterPath?.let { poster ->
                AsyncImage(
                    modifier = Modifier
                        .width(150.dp)
                        .height(225.dp),
                    model = ApiConfig.IMAGE_BASE_URL_500W + poster,
                    contentDescription = "${movie.title} poster",
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Overview
            Text(
                text = "Overview",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Genres
            if (movie.genres.isNotEmpty()) {
                Text(
                    text = "Genres",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(4.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    movie.genres.forEach { genre ->
                        AssistChip(
                            onClick = { },
                            label = { Text(genre) }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}