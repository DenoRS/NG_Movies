import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ie.rubberduck.components.text.title.large.NGTitleLarge

@Composable
fun NGTitleLargePrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
    maxLines: Int = 1,
) {
    NGTitleLarge(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = textAlign,
        maxLines = maxLines,
    )
}


@Preview(
    name = "NGTitleLargePrimary",
    showBackground = true,
)
@Composable
private fun NGTitleLargePrimaryPreview() {
    NGTitleLargePrimary(text = "NG Title Large Primary text")
}
