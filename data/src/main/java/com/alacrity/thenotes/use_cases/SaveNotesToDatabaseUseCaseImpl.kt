package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.repository.Repository
import javax.inject.Inject

class SaveNotesToDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
): SaveNotesToDatabaseUseCase {

    override suspend fun invoke(notes: List<Note>) = repository.saveNotesToDatabase(notes)

}