package com.alacrity.thenotes.ui.edit.models

import com.alacrity.thenotes.BaseEvent
import com.alacrity.thenotes.ui.edit.EditViewModel

sealed class EditEvent: BaseEvent {

    object EnterScreen : EditEvent()

    object CreateBlankNote : EditEvent()

}

fun EditViewModel.enterScreen() {
    obtainEvent(EditEvent.EnterScreen)
}
