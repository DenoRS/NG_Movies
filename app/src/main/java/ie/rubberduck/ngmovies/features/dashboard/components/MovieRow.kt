package ie.rubberduck.ngmovies.features.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieRow(
    movies: List<MovieModel>
    // change to domain model
) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        items(movies) { movie ->
            MovieCard(
                imageUrl = movie.imageUrl,
                title = movie.title,
                duration = movie.duration,
                onClick = {},
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
private fun MovieRowPreview() {
    MaterialTheme {
        MovieRow(movies = sampleMoviesList)
    }
}