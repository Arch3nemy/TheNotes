package com.alacrity.thenotes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alacrity.thenotes.Destinations.HOME_ROUTE
import com.alacrity.thenotes.Destinations.SECOND_SCREEN
import com.alacrity.thenotes.ui.main.MainScreen
import com.alacrity.thenotes.ui.main.MainViewModel

object Destinations {
    const val HOME_ROUTE = "home"
    const val SECOND_SCREEN = "details"
    const val THIRD_SCREEN = "details_v2"
}

@Composable
fun AppNavGraph(
    homeViewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(HOME_ROUTE) {
            MainScreen(
                viewModel = homeViewModel,
            )
        }

        composable(
            "${SECOND_SCREEN}/{sId}",
            arguments = listOf(navArgument("sId") {
                type = NavType.StringType
            })
        ) {
            SecondScreen(homeViewModel, it.arguments?.getString("tId") ?: "")
        }


    }
}

@Composable
fun SecondScreen(viewModel: MainViewModel, arg: String) {

}

/**
 * navHostController.navigate("userPage?apiResponse=${Uri.encode(gson.toJson(apiResponse))}")
 */