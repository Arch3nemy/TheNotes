package com.alacrity.thenotes.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {

        @Query("SELECT * FROM notes")
        fun getAll(): List<NoteTableItem>

        @Insert
        fun insertAll(notes: List<NoteTableItem>)

        @Delete
        fun delete(note: NoteTableItem)

        @Insert
        fun insert(note: NoteTableItem)

}