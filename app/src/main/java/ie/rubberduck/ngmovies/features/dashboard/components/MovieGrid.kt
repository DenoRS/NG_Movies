package ie.rubberduck.ngmovies.features.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ie.rubberduck.ngmovies.ext.roundTo1Decimal
import ie.rubberduck.ngmovies.features.dashboard.MovieUiModel

@Composable
fun MovieGrid(
    movies: List<MovieUiModel>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies) { movie ->
            MovieCard(
                imageUrl = movie.posterPath,
                title = movie.title,
                rating = movie.voteAverage.roundTo1Decimal().toString()
            )
        }
    }
}

private val sampleMoviesList = List(6) { index ->
    MovieUiModel(
        id = index,
        title = "Movie #$index",
        overview = "overview",
        posterPath = "https://dummyimage.com/250x250/cccccc/000000.png&text=Hello there $index",
        releaseDate = "25-09-2025",
        voteAverage = 2.5,
    )
}

@Preview(showBackground = true)
@Composable
private fun MovieGridPreview() {
    MaterialTheme {
        MovieGrid(
            movies = sampleMoviesList
        )
    }
}