package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.repository.Repository
import javax.inject.Inject

class RemoveNoteFromDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
) : RemoveNoteFromDatabaseUseCase {

    override suspend fun invoke(note: Note) = repository.removeNoteFromDatabase(note)

}