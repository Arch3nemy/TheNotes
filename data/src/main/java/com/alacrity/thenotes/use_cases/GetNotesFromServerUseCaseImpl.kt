package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.repository.Repository
import javax.inject.Inject

class GetNotesFromServerUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetNotesFromServerUseCase {

    override suspend fun invoke() = repository.getNotesFromServer()

}