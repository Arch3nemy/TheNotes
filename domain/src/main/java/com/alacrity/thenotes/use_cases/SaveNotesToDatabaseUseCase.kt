package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.entity.Note

interface SaveNotesToDatabaseUseCase {

    suspend operator fun invoke(notes: List<Note>)

}