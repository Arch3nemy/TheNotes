package com.alacrity.thenotes.util.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.alacrity.thenotes.ui.home.HomeViewModel
import com.alacrity.thenotes.util.workers.HelloWorldWorker.Factory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class HelloWorldWorker @AssistedInject constructor(
    @Assisted private val fooContext: Context,
    @Assisted private val params: WorkerParameters,
    private val viewModel: HomeViewModel
) : Worker(fooContext, params) {

    override fun doWork(): Result {
        viewModel.updateDates()
        return Result.success()
    }

    @AssistedFactory
    interface Factory {
        fun create(appContext: Context, params: WorkerParameters): HelloWorldWorker
    }
}



