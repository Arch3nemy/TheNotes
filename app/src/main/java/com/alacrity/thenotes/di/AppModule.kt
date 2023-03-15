package com.alacrity.thenotes.di

import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import com.alacrity.thenotes.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context.applicationContext

    @Provides
    fun provideResources(): Resources = context.resources

    @Singleton
    @Provides
    fun provideUserDatabase(
        app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "notes"
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.notesDao()



}