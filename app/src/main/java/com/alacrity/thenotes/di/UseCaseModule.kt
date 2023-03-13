package com.alacrity.thenotes.di

import com.alacrity.thenotes.use_cases.GetSimpleResponseUseCase
import com.alacrity.thenotes.use_cases.GetSimpleResponseUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindNewMessageReceivedUseCase(impl: GetSimpleResponseUseCaseImpl): GetSimpleResponseUseCase

}