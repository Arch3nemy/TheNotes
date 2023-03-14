package com.alacrity.thenotes.utils

import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.room.NoteTableItem

fun Note.toNoteTableItem(): NoteTableItem = NoteTableItem(id, title, description, date)

fun NoteTableItem.toNote(): Note = Note(id, title, description, date)