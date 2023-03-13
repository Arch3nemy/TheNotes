package com.alacrity.thenotes.ui.main.models

import com.alacrity.thenotes.BaseEvent
import com.alacrity.thenotes.ui.main.MainViewModel

sealed class MainEvent: BaseEvent {

    object EnterScreen : MainEvent()

}

fun MainViewModel.enterScreen() {
    obtainEvent(MainEvent.EnterScreen)
}