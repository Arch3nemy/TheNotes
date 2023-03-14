package com.alacrity.thenotes.util

import com.alacrity.thenotes.BaseEvent
import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.view_states.BaseViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.firstOrNull
import java.text.SimpleDateFormat
import java.util.*

fun <T : BaseEvent, V : BaseViewState> BaseViewModel<T, V>.createBlankNote(block: (Note) -> Unit) {
    launch {
        val hoursAndMinutesFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
        val note = (Note(
            UUID.randomUUID().toString(),
            "Blank title",
            "Blank description",
            hoursAndMinutesFormat.format(Date())
        ))

        block.invoke(note)
    }
}