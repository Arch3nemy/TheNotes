package com.alacrity.thenotes.di.base

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(impl: DaggerViewModelFactory): ViewModelProvider.Factory

}