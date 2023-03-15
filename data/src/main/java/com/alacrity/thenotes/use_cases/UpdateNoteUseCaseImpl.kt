package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.repository.Repository
import javax.inject.Inject

class UpdateNoteUseCaseImpl @Inject constructor(
    private val repository: Repository
): UpdateNoteUseCase {

    override suspend fun invoke(note: Note) = repository.updateNote(note)

}