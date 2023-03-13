package com.alacrity.thenotes.repository

import com.alacrity.thenotes.entity.Note
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    override suspend fun getNotesFromServer(): List<Note> {
        delay(5000)
        return listOf(
            Note(0, "title1", "desc1", Date()),
            Note(1, "title2", "desc2", Date())
        )
    }

}