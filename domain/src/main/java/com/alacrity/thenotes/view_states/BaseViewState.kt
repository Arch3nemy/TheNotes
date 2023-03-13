package com.alacrity.thenotes.view_states

sealed interface BaseViewState {



    fun getBaseState(): BaseViewState = Loading

    companion object {
        object Loading : BaseViewState
    }
}