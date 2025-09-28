package ie.rubberduck.ngmovies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ie.rubberduck.ngmovies.features.dashboard.DashboardScreen
import ie.rubberduck.ngmovies.features.details.DetailsScreen
import ie.rubberduck.ngmovies.features.search.SearchScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(
                onMovieClick = { movieId ->
                    navController.navigate("details/$movieId")
                },
                onSearchClick = {
                    navController.navigate("search")
                }
            )
        }

        composable(
            route = "details/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            DetailsScreen(
                movieId = movieId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(route = "search") {
            SearchScreen(
                onMovieClick = { movieId ->
                    navController.navigate("details/$movieId")
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}