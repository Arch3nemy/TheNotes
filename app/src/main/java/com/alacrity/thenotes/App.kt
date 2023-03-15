package com.alacrity.thenotes

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.alacrity.thenotes.di.AppComponent
import com.alacrity.thenotes.di.AppModule
import com.alacrity.thenotes.di.DaggerAppComponent
import com.alacrity.thenotes.util.workers.SampleWorkerFactory
import timber.log.Timber
import javax.inject.Inject

class App : Application() {


    @Inject
    lateinit var sampleWorkerFactory: SampleWorkerFactory

    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
            .apply { inject(this@App) }
        super.onCreate()

        val workManagerConfig = Configuration.Builder()
            .setWorkerFactory(sampleWorkerFactory)
            .build()
        WorkManager.initialize(this, workManagerConfig)
    }
    companion object {
        lateinit var appComponent: AppComponent
    }

}