package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.repository.Repository
import javax.inject.Inject

class GetSimpleResponseUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetSimpleResponseUseCase {

    override suspend fun invoke() = repository.getNotesFromServer()

}