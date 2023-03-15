package com.alacrity.thenotes.ui.edit

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.alacrity.thenotes.Destinations.EDIT_SCREEN_ROUTE
import com.alacrity.thenotes.Destinations.HOME_ROUTE
import com.alacrity.thenotes.NEW_NOTE_ARG_KEY
import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.room.NoteTableItem
import com.alacrity.thenotes.ui.edit.models.enterScreen
import com.alacrity.thenotes.ui.edit.models.saveEditedNote
import com.alacrity.thenotes.ui.edit.screens.EditScreen
import com.alacrity.thenotes.ui.home.screens.ErrorScreen
import com.alacrity.thenotes.ui.home.screens.LoadingScreen
import com.alacrity.thenotes.utils.toNoteTableItem
import com.alacrity.thenotes.view_states.EditViewState

@Composable
fun MainEditScreen(
    viewModel: EditViewModel,
    note: NoteTableItem?,
    navController: NavController
) {
    val state by viewModel.viewState.collectAsState()

    when (state) {
        is EditViewState.Loading -> {
            LoadingScreen()
        }
        is EditViewState.FinishedLoading -> {
            note?.let {
                EditScreen(
                    it,
                    onSaveClick = { note ->
                        viewModel.saveEditedNote(note)
                        navController.navigateToHomeScreen(note)
                    },
                    onBackAction = { navController.popBackStack() }
                )
            }
        }
        is EditViewState.Error -> {
            ErrorScreen(exception = (state as EditViewState.Error).exception)
        }
    }

    LaunchedEffect(key1 = state, block = {
        viewModel.enterScreen()
    })

    BackHandler {
        navController.popBackStack()
    }
}

private fun NavController.navigateToHomeScreen(note: Note) {
    currentBackStackEntry?.savedStateHandle?.apply {
        set(key = NEW_NOTE_ARG_KEY, value = note.toNoteTableItem())
    }
    clearBackStack(EDIT_SCREEN_ROUTE)
    navigate(HOME_ROUTE)
}
