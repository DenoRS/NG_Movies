package ie.rubberduck.ngmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ie.rubberduck.ngmovies.navigation.NavGraph
import ie.rubberduck.theming.theme.NGMoviesTheme

@AndroidEntryPoint
class NavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NGMoviesTheme {
                NavGraph()
            }
        }
    }
}