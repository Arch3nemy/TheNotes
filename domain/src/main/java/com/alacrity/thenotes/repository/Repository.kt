package com.alacrity.thenotes.repository

import com.alacrity.thenotes.entity.Note

interface Repository {

    suspend fun getNotesFromServer(): List<Note>

    suspend fun saveNotesToDatabase(notes: List<Note>)

    suspend fun getNotesFromDatabase(): List<Note>

    suspend fun removeNoteFromDatabase(note: Note)

    suspend fun saveNoteToDatabase(note: Note)

    suspend fun updateNote(note: Note)
}