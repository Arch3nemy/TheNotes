package com.alacrity.thenotes.di

import com.alacrity.thenotes.repository.Repository
import com.alacrity.thenotes.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(impl: RepositoryImpl): Repository

}