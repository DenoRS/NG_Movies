package ie.rubberduck.ngmovies.features.dashboard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    rating: String,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick),
    ) {
        Card(
            modifier = Modifier
                .height(160.dp)
                .width(120.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = imageUrl,
                contentDescription = title,
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.width(120.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = "$rating ⭐",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MovieCardPreview() {
    MaterialTheme {
        MovieCard(
            imageUrl = "https://dummyimage.com/250x250/cccccc/000000.png&text=Hello there",
            title = "Movie 1",
            rating = "2"
        )
    }
}