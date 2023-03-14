package com.alacrity.thenotes.ui.edit

import androidx.activity.compose.BackHandler
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.alacrity.thenotes.room.NoteTableItem
import com.alacrity.thenotes.ui.edit.models.enterScreen
import com.alacrity.thenotes.ui.edit.screens.EditScreen
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

        }
        is EditViewState.FinishedLoading -> {
            note?.let {
                EditScreen(
                    it,
                    onBackAction = { navController.popBackStack() }
                )
            }
        }
        is EditViewState.Error -> {

        }
    }

    LaunchedEffect(key1 = state, block = {
        viewModel.enterScreen()
    })

    BackHandler {
        navController.popBackStack()
    }
}