package com.alacrity.thenotes.ui.main

import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.ui.main.models.MainEvent
import com.alacrity.thenotes.use_cases.GetSimpleResponseUseCase
import com.alacrity.thenotes.util.BaseViewModel
import com.alacrity.thenotes.view_states.MainViewState
import com.alacrity.thenotes.view_states.MainViewState.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSimpleResponseUseCase: GetSimpleResponseUseCase
) : BaseViewModel<MainEvent, MainViewState>(Loading) {

    val viewState: StateFlow<MainViewState> = _viewState

    private val _notesFlow: MutableSharedFlow<List<Note>> = MutableSharedFlow()
    val notesFlow: SharedFlow<List<Note>> = _notesFlow

    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _viewState.value) {
            is Loading -> currentState.reduce(event)
            is Error -> currentState.reduce(event)
            is FinishedLoading -> currentState.reduce(event)
            is NoItems -> currentState.reduce(event)
            is Refreshing -> currentState.reduce(event)
        }
    }

    private fun Loading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.EnterScreen -> {
                loadNotes()
            }
            else -> Unit
        }
    }

    private fun Error.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun FinishedLoading.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun NoItems.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun Refreshing.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun loadNotes() {
        launch(
            logError = "Error getting notes from server",
            logSuccess = "Successfully received notes from server",
            onSuccess = { notes ->
                onObtainNotes(notes)
            },
            onFailure = {
                _viewState.value = Error(it)
            }
        ) {
            getSimpleResponseUseCase()
        }

    }

    private fun onObtainNotes(notesList: List<Note>) {
        launch {
            _notesFlow.emit(notesList)
            _viewState.value = FinishedLoading(notesList)
        }
    }

}