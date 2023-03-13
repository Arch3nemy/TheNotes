package com.alacrity.thenotes.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alacrity.thenotes.ui.main.models.enterScreen
import com.alacrity.thenotes.ui.main.screens.HomeScreen
import com.alacrity.thenotes.view_states.MainViewState

@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {

    val state by viewModel.viewState.collectAsState()
    val notes by viewModel.notesFlow.collectAsState(initial = emptyList())

    when (state) {
        MainViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LinearProgressIndicator()
            }
        }
        is MainViewState.FinishedLoading -> {
            HomeScreen(
                notesList = notes,
                onFabClick = { }
            )
        }
        is MainViewState.NoItems -> {

        }
        is MainViewState.Error -> {
            /* ShowErrorView */
        }

        is MainViewState.Refreshing -> {

        }
        else -> Unit
    }

    LaunchedEffect(key1 = state, block = {
        viewModel.enterScreen()
    })

}
