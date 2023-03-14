package com.alacrity.thenotes.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteTableItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

}