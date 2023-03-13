package com.alacrity.thenotes

import android.app.Application
import com.alacrity.thenotes.di.AppComponent
import com.alacrity.thenotes.di.AppModule
import com.alacrity.thenotes.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
            .apply { inject(this@App) }
    }
    companion object {
        lateinit var appComponent: AppComponent
    }

}