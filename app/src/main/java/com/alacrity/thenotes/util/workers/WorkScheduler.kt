package com.alacrity.thenotes.util.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.*
import java.util.concurrent.TimeUnit

class WorkScheduler {

    //Midnight
    private val hourOfTheDay = 0

    private val myConstraints: Constraints =
        Constraints.Builder().setRequiresStorageNotLow(true).build()

    private val workRequest = PeriodicWorkRequest.Builder(
        UpdateNotesWorker::class.java,
        1L, TimeUnit.DAYS,
        calculateFlex(), TimeUnit.MILLISECONDS
    )
        .setConstraints(myConstraints)
        .build()


    fun initUpdateDatesWork(context: Context) {
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            UPDATE_DATES_WORKER_TAG,
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }


    private fun calculateFlex(): Long {
        val cal1: Calendar = Calendar.getInstance()
        cal1.set(Calendar.HOUR_OF_DAY, hourOfTheDay)
        cal1.set(Calendar.MINUTE, 0)
        cal1.set(Calendar.SECOND, 0)

        val cal2: Calendar = Calendar.getInstance()
        if (cal2.timeInMillis < cal1.timeInMillis) {
            cal2.timeInMillis = cal2.timeInMillis + TimeUnit.DAYS.toMillis(1L)
        }
        val delta: Long = cal2.timeInMillis - cal1.timeInMillis
        return if (delta > PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) delta else PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS
    }

    companion object {
        const val UPDATE_DATES_WORKER_TAG = "worker_update_dates"
    }

}