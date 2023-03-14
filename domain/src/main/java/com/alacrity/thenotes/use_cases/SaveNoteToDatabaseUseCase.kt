package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.entity.Note

interface SaveNoteToDatabaseUseCase {

    suspend operator fun invoke(note: Note)

}