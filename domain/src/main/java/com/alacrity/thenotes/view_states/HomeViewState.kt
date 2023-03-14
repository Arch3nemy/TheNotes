package com.alacrity.thenotes.view_states

import com.alacrity.thenotes.entity.Note

sealed class HomeViewState: BaseViewState {
    object Loading : HomeViewState()
    data class Error(val exception: Throwable? = null, val message: String = "") : HomeViewState()
    data class FinishedLoading(val note: List<Note>) : HomeViewState()
}