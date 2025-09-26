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

@Composable
fun MovieGrid(
    movies: List<MovieModel>
    // change to domain
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies) { movie ->
            MovieCard(
                imageUrl = movie.imageUrl,
                title = movie.title,
                duration = movie.duration
            )
        }
    }
}

private val sampleMoviesList = List(6) { index ->
    MovieModel(
        imageUrl = "https://dummyimage.com/250x250/cccccc/000000.png&text=Hello there $index",
        title = "Movie #$index",
        duration = "2h 30m",
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