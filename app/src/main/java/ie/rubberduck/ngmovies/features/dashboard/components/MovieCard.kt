package ie.rubberduck.ngmovies.features.dashboard.components

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import coil3.compose.AsyncImage

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    duration: String,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//            colors = TODO(),
//            border = TODO()
        ) {
            AsyncImage(
                modifier = Modifier.wrapContentSize(),
                model = "https://dummyimage.com/250x250/cccccc/000000.png&text=Hello there",
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
        )
        Text(
            text = duration,
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
            duration = "2h 30m"
        )
    }
}