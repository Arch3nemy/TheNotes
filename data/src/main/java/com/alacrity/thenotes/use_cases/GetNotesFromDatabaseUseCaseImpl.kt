package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.repository.Repository
import javax.inject.Inject

class GetNotesFromDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetNotesFromDatabaseUseCase {

    override suspend fun invoke() = repository.getNotesFromDatabase()

}