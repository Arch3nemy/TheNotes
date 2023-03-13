package com.alacrity.thenotes.use_cases

import com.alacrity.thenotes.entity.Note

interface GetSimpleResponseUseCase {

    suspend operator fun invoke(): List<Note>

}