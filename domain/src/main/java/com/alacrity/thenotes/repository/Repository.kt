package com.alacrity.thenotes.repository

import com.alacrity.thenotes.entity.Note

interface Repository {

    suspend fun getNotesFromServer(): List<Note>

}