package com.alacrity.thenotes.view_states


sealed class EditViewState : BaseViewState {
    object Loading : EditViewState()
    object FinishedLoading : EditViewState()
    data class Error(val exception: Throwable? = null, val message: String = "") : EditViewState()

}

