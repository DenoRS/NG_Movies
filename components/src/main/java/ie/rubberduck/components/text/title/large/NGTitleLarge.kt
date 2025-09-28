package ie.rubberduck.components.text.title.large

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun NGTitleLarge(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = 1,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = color,
        fontWeight = FontWeight.Medium,
        modifier = modifier,
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Preview(
    name = "NGTitleLarge",
    showBackground = true,
)
@Composable
private fun RBTitleLargePreview() {
    NGTitleLarge(
        text = "NG Title Large text",
        color = MaterialTheme.colorScheme.onSurface,
    )
}
