package com.alacrity.thenotes.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.alacrity.thenotes.Destinations.EDIT_SCREEN_ROUTE
import com.alacrity.thenotes.NOTE_ARG_KEY
import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.ui.home.screens.HomeScreen
import com.alacrity.thenotes.ui.home.models.createBlankNote
import com.alacrity.thenotes.ui.home.models.enterScreen
import com.alacrity.thenotes.ui.home.models.removeNote
import com.alacrity.thenotes.ui.home.models.updateNote
import com.alacrity.thenotes.ui.home.screens.ErrorScreen
import com.alacrity.thenotes.ui.home.screens.LoadingScreen
import com.alacrity.thenotes.ui.home.screens.NoItemsScreen
import com.alacrity.thenotes.utils.toNoteTableItem
import com.alacrity.thenotes.view_states.HomeViewState

@Composable
fun MainHomeScreen(
    viewModel: HomeViewModel,
    updatedNote: Note?,
    navController: NavController
) {

    if(updatedNote != null) viewModel.updateNote(updatedNote)

    val state by viewModel.viewState.collectAsState()
    val notes by viewModel.notesFlow.collectAsState(initial = emptyList())

    when (state) {
        HomeViewState.Loading -> {
          LoadingScreen()
        }
        is HomeViewState.FinishedLoading -> {
            HomeScreen(
                notesList = notes,
                onFabClick = { viewModel.createBlankNote() },
                onRemoveNote = { note -> viewModel.removeNote(note) }
            ) { note -> navController.navigateToEditScreen(note) }
        }
        is HomeViewState.Error -> {
            ErrorScreen(exception = (state as HomeViewState.Error).exception)
        }
        is HomeViewState.NoItems -> {
            NoItemsScreen()
        }
        else -> Unit
    }

    LaunchedEffect(key1 = state, block = {
        viewModel.enterScreen()
    })

}

private fun NavController.navigateToEditScreen(note: Note) {
    currentBackStackEntry?.savedStateHandle?.apply {
        set(key = NOTE_ARG_KEY, value = note.toNoteTableItem())
    }
    navigate(EDIT_SCREEN_ROUTE)
}
