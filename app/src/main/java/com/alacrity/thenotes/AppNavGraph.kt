package com.alacrity.thenotes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alacrity.thenotes.Destinations.HOME_ROUTE
import com.alacrity.thenotes.Destinations.EDIT_SCREEN_ROUTE
import com.alacrity.thenotes.room.NoteTableItem
import com.alacrity.thenotes.ui.edit.EditViewModel
import com.alacrity.thenotes.ui.edit.MainEditScreen
import com.alacrity.thenotes.ui.home.MainHomeScreen
import com.alacrity.thenotes.ui.home.HomeViewModel
import com.alacrity.thenotes.util.internet.ConnectivityObserver
import com.alacrity.thenotes.utils.toNote

object Destinations {
    const val HOME_ROUTE = "home"
    const val EDIT_SCREEN_ROUTE = "edit"
}

const val NOTE_ARG_KEY = "arg_key"
const val NEW_NOTE_ARG_KEY = "new_arg_key"

@Composable
fun AppNavGraph(
    homeViewModel: HomeViewModel,
    editViewModel: EditViewModel,
    networkStatus: ConnectivityObserver.Status,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(HOME_ROUTE) {
            val note = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<NoteTableItem>(key = NEW_NOTE_ARG_KEY)

            MainHomeScreen(
                viewModel = homeViewModel,
                updatedNote = note?.toNote(),
                navController = navController,
                networkStatus = networkStatus
            )
        }

        composable(EDIT_SCREEN_ROUTE) {
            val note = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<NoteTableItem>(key = NOTE_ARG_KEY)

            MainEditScreen(
                viewModel = editViewModel,
                note = note,
                navController = navController,
            )
        }

    }
}
