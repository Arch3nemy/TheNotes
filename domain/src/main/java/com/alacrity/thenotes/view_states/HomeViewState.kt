package com.alacrity.thenotes.view_states

import com.alacrity.thenotes.entity.Note

sealed class HomeViewState: BaseViewState {
    object Loading : HomeViewState()
    object NoItems : HomeViewState()
    object WaitingForInternet : HomeViewState()
    data class Error(val exception: Throwable? = null, val message: String = "") : HomeViewState()
    data class FinishedLoading(val note: List<Note>, val isNetworkAvailable: Boolean) : HomeViewState()
}