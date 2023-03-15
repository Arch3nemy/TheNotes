package com.alacrity.thenotes.di

import com.alacrity.thenotes.App
import com.alacrity.thenotes.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        UseCaseModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(app: App)

}