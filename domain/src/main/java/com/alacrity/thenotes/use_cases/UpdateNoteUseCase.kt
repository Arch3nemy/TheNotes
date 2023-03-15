package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.entity.Note

interface UpdateNoteUseCase {

    suspend operator fun invoke(note: Note)

}