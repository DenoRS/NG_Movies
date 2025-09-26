package ie.rubberduck.components.toolbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NGTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescription: String? = "",
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String? = "",
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            if (title != null)
                Column(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = title)
                }
        },
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIconContentDescription,
                    )
                }
            }
        },
        actions = {
            if (actionIcon != null) {
                IconButton(onClick = onActionClick) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = actionIconContentDescription,
                    )
                }
            }
        },
        colors = colors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "NG TopAppBar")
@Composable
private fun NGTopAppBarPreview() {
    NGTopAppBar(
        title = "Some title",
        navigationIcon = null,
        actionIcon = Icons.Filled.Search
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "NG TopAppBar")
@Composable
private fun NGTopAppBarWithNavIconPreview() {
    NGTopAppBar(
        title = "Some title",
        navigationIcon = Icons.Filled.Menu,
        actionIcon = Icons.Filled.Search
    )
}