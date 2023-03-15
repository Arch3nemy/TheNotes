package com.alacrity.thenotes.ui.edit.models

import com.alacrity.thenotes.BaseEvent
import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.ui.edit.EditViewModel

sealed class EditEvent: BaseEvent {

    object EnterScreen : EditEvent()

    data class SaveEditedNote(val note: Note) : EditEvent()

}

fun EditViewModel.enterScreen() {
    obtainEvent(EditEvent.EnterScreen)
}

fun EditViewModel.saveEditedNote(note: Note) {
    obtainEvent(EditEvent.SaveEditedNote(note))
}