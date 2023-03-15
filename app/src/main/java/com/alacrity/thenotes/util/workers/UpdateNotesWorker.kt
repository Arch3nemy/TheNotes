package com.alacrity.thenotes.util.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alacrity.thenotes.use_cases.GetNotesFromDatabaseUseCase
import com.alacrity.thenotes.use_cases.SaveNotesToDatabaseUseCase
import com.alacrity.thenotes.use_cases.UpdateNoteUseCase
import com.alacrity.thenotes.utils.getUpdatedNoteDayMonthAndYear
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class UpdateNotesWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val getNotesFromDatabaseUseCase: GetNotesFromDatabaseUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = coroutineScope {
        val notes = getNotesFromDatabaseUseCase() as MutableList
        notes.apply {
            notes.replaceAll { note ->
                if (note.date.length < 6) note.copy(date = getUpdatedNoteDayMonthAndYear())
                else note
            }
        }
        notes.forEach {note ->
            updateNoteUseCase(note)
        }
        Result.success()
    }

    @AssistedFactory
    interface Factory {
        fun create(appContext: Context, params: WorkerParameters): UpdateNotesWorker
    }
}



