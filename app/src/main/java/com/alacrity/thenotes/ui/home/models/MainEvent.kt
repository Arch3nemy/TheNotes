package com.alacrity.thenotes.ui.home.models

import com.alacrity.thenotes.BaseEvent
import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.ui.home.HomeViewModel
import com.alacrity.thenotes.util.internet.ConnectivityObserver

sealed class MainEvent : BaseEvent {

    object CreateBlankNote : MainEvent()

    object NetworkAvailable : MainEvent()

    data class EnterScreen(val networkStatus: ConnectivityObserver.Status) : MainEvent()

    data class RemoveNote(val note: Note) : MainEvent()

    data class UpdateNote(val note: Note) : MainEvent()

}

fun HomeViewModel.enterScreen(networkStatus: ConnectivityObserver.Status) {
    obtainEvent(MainEvent.EnterScreen(networkStatus))
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
fun HomeViewModel.onNetworkAvailable() {
    obtainEvent(MainEvent.NetworkAvailable)
}
