package ie.rubberduck.components.toolbar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NGSearchAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    navigationIconContentDescription: String? = null,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onSearchTriggered: (String) -> Unit = {},
    startInSearchMode: Boolean = false
) {
    var isSearchMode by remember { mutableStateOf(startInSearchMode) }
    var searchQuery by remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(isSearchMode) {
        if (isSearchMode) {
            focusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    TopAppBar(
        title = {
            if (isSearchMode) {
                BasicTextField(
                    modifier = Modifier
                        .padding(4.dp)
                        .focusRequester(focusRequester),
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    singleLine = true,
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                )
            } else {
                title?.let { Text(text = it) }
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                // If in search mode, clear query and exit search mode first
                if (isSearchMode) {
                    isSearchMode = false
                    searchQuery = ""
                }
                // Always trigger navigation
                onNavigationClick()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = navigationIconContentDescription ?: "Back"
                )
            }
        },
        actions = {
            if (isSearchMode) {
                IconButton(onClick = { searchQuery = "" }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear search"
                    )
                }
            } else {
                IconButton(onClick = { isSearchMode = true }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            }
        },
        colors = colors
    )

    // Trigger this callback whenever user types
    if (isSearchMode) {
        LaunchedEffect(searchQuery) {
            onSearchTriggered(searchQuery)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NGSearchAppBarPreview() {
    MaterialTheme {
        NGSearchAppBar(
            title = "Challenges",
            navigationIconContentDescription = "Menu",
            onNavigationClick = { /* no-op */ },
            onSearchTriggered = { query ->
                println("Searching: $query")
            }
        )
    }
}

