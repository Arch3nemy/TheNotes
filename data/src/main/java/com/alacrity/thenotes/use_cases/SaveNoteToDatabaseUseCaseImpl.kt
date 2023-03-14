package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.repository.Repository
import javax.inject.Inject

class SaveNoteToDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
): SaveNoteToDatabaseUseCase {

    override suspend fun invoke(note: Note) = repository.saveNoteToDatabase(note)

}