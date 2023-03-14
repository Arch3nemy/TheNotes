package com.alacrity.thenotes.di

import com.alacrity.thenotes.use_cases.*
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindGetNotesFromServerUseCase(impl: GetNotesFromServerUseCaseImpl): GetNotesFromServerUseCase

    @Binds
    fun bindGetNotesFromDatabaseUseCase(impl: GetNotesFromDatabaseUseCaseImpl): GetNotesFromDatabaseUseCase

    @Binds
    fun bindRemoveNoteFromDatabaseUseCase(impl: RemoveNoteFromDatabaseUseCaseImpl): RemoveNoteFromDatabaseUseCase

    @Binds
    fun bindSaveNotesToDatabaseUseCase(impl: SaveNotesToDatabaseUseCaseImpl): SaveNotesToDatabaseUseCase

    @Binds
    fun bindSaveNoteToDatabaseUseCase(impl: SaveNoteToDatabaseUseCaseImpl): SaveNoteToDatabaseUseCase



}