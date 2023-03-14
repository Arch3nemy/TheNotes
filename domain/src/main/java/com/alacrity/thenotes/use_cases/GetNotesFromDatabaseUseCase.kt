package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.entity.Note

interface GetNotesFromDatabaseUseCase {

    suspend operator fun invoke(): List<Note>

}