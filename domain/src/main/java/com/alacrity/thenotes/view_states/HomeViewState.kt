package com.alacrity.thenotes.view_states

import com.alacrity.thenotes.entity.Note


sealed class MainViewState: BaseViewState {
    object Loading : MainViewState()
    object Refreshing: MainViewState()
    data class Error(val exception: Throwable? = null, val message: String = "") : MainViewState()
    object NoItems: MainViewState()
    data class FinishedLoading(val note: List<Note>) : MainViewState()
}