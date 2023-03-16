package com.alacrity.thenotes.repository

import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.room.AppDatabase
import com.alacrity.thenotes.utils.dateToProperString
import com.alacrity.thenotes.utils.toNoteTableItem
import com.alacrity.thenotes.utils.toNote
import kotlinx.coroutines.delay
import java.time.temporal.ChronoUnit
import java.util.*
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : Repository {

    override suspend fun getNotesFromServer(): List<Note> {
        delay(REQUEST_DELAY)
        return generateStabbedData()
    }

    override suspend fun saveNotesToDatabase(notes: List<Note>) = db.notesDao().insertAll(notes.map { it.toNoteTableItem() })

    override suspend fun getNotesFromDatabase(): List<Note> {
        delay(REQUEST_DELAY)
        return db.notesDao().getAll().map { it.toNote() }
    }

    override suspend fun removeNoteFromDatabase(note: Note) = db.notesDao().delete(note.toNoteTableItem())

    override suspend fun saveNoteToDatabase(note: Note) = db.notesDao().insert(note.toNoteTableItem())

    override suspend fun updateNote(note: Note) = db.notesDao().update(note.toNoteTableItem())

    private fun generateStabbedData(): List<Note> {
        val date = Date()
        val secondDate = Date.from(date.toInstant().minus(1, ChronoUnit.DAYS))

        return listOf(
            Note(
                "TEST_ID0",
                "Example of really long title to be sure it will be displayed properly",
                "description",
                dateToProperString(date)
            ),
            Note(
                "TEST_ID1",
                "title",
                "Example of really long description to be sure it will be displayed properly",
                dateToProperString(secondDate)
            )
        )
    }

    companion object {
        const val REQUEST_DELAY = 5000L
    }

}