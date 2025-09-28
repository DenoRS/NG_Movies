package ie.rubberduck.components.text.body.large

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NGBodyLarge(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
    fontWeight: FontWeight? = MaterialTheme.typography.bodyLarge.fontWeight,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = fontWeight,
        lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
        textAlign = textAlign
    )
}

@Preview(showBackground = true, name = "NGBodyLarge - Light")
@Composable
fun NGBodyLargePreview() {
    MaterialTheme {
        Column(modifier = Modifier.padding(4.dp)) {
            NGBodyLarge(text = "This is a preview of NGBodyLarge with a plain String.")
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "NGBodyLarge - Dark")
@Composable
fun NGBodyLargePreviewDark() {
    MaterialTheme {
        Column(modifier = Modifier.padding(4.dp)) {
            NGBodyLarge(text = "This is a dark-preview of NGBodyLarge with a plain String.")
        }
    }
}