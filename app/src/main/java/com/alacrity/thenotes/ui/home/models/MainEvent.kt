package com.alacrity.thenotes.ui.home.models

import com.alacrity.thenotes.BaseEvent
import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.ui.home.HomeViewModel

sealed class MainEvent : BaseEvent {

    object EnterScreen : MainEvent()

    object CreateBlankNote : MainEvent()

    data class RemoveNote(val note: Note) : MainEvent()

    data class UpdateNote(val note: Note) : MainEvent()

}

fun HomeViewModel.enterScreen() {
    obtainEvent(MainEvent.EnterScreen)
}

fun HomeViewModel.createBlankNote() {
    obtainEvent(MainEvent.CreateBlankNote)
}

fun HomeViewModel.removeNote(note: Note) {
    obtainEvent(MainEvent.RemoveNote(note))
}

fun HomeViewModel.updateNote(note: Note) {
    obtainEvent(MainEvent.UpdateNote(note))
}