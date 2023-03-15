package com.alacrity.thenotes.ui.edit

import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.ui.edit.models.EditEvent
import com.alacrity.thenotes.use_cases.UpdateNoteUseCase
import com.alacrity.thenotes.util.BaseViewModel
import com.alacrity.thenotes.view_states.EditViewState
import com.alacrity.thenotes.view_states.EditViewState.*
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val updateNoteUseCase: UpdateNoteUseCase
) : BaseViewModel<EditEvent, EditViewState>(Loading) {


    val viewState: StateFlow<EditViewState> = _viewState

    override fun obtainEvent(event: EditEvent) {
        when (val currentState = _viewState.value) {
            is Error -> currentState.reduce(event)
            is FinishedLoading -> currentState.reduce(event)
            is Loading -> currentState.reduce(event)
        }
    }

    private fun Loading.reduce(event: EditEvent) {
        logReduce(event)
        when (event) {
            is EditEvent.EnterScreen -> {
                _viewState.value = FinishedLoading
            }
            else -> Unit
        }
    }

    private fun FinishedLoading.reduce(event: EditEvent) {
        logReduce(event)
        when(event) {
            is EditEvent.SaveEditedNote -> {
                saveEditedNote(event.note)
            }
            else -> Unit
        }
    }

    private fun Error.reduce(event: EditEvent) {
        logReduce(event)
    }

    private fun saveEditedNote(note: Note) {
        launch {
            updateNoteUseCase(note)
        }
    }

}