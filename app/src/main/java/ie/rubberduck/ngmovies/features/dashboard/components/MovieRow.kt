package ie.rubberduck.ngmovies.features.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ie.rubberduck.data.remote.api.ApiConfig
import ie.rubberduck.ngmovies.ext.roundTo1Decimal
import ie.rubberduck.ngmovies.features.dashboard.MovieUiModel

@Composable
fun MovieRow(
    movies: List<MovieUiModel>,
    onMovieClick: (Int) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(movies) { movie ->
            MovieCard(
                imageUrl = ApiConfig.IMAGE_BASE_URL_ORIGINAL + movie.posterPath,
                title = movie.title,
                rating = movie.voteAverage.roundTo1Decimal().toString(),
                onClick = { onMovieClick(movie.id) },
            )
        }
    }

}

private val sampleMoviesList = List(6) { index ->
    MovieUiModel(
        id = 1,
        posterPath = "https://dummyimage.com/250x250/cccccc/000000.png&text=Hello there $index",
        title = "Movie #$index",
        releaseDate = "25-09-2025",
        overview = "overview",
        voteAverage = 4.5
    )
}


@Preview(showBackground = true)
@Composable
private fun MovieRowPreview() {
    MaterialTheme {
        MovieRow(
            movies = sampleMoviesList,
            onMovieClick = {}
        )
    }
}