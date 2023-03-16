package com.alacrity.thenotes.ui.home

import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.ui.home.models.MainEvent
import com.alacrity.thenotes.use_cases.*
import com.alacrity.thenotes.util.BaseViewModel
import com.alacrity.thenotes.util.createBlankNote
import com.alacrity.thenotes.util.internet.ConnectivityObserver.Status.*
import com.alacrity.thenotes.view_states.HomeViewState
import com.alacrity.thenotes.view_states.HomeViewState.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getNotesFromServerUseCase: GetNotesFromServerUseCase,
    private val saveNotesToDatabaseUseCase: SaveNotesToDatabaseUseCase,
    private val getNotesFromDatabaseUseCase: GetNotesFromDatabaseUseCase,
    private val removeNoteFromDatabaseUseCase: RemoveNoteFromDatabaseUseCase,
    private val saveNoteToDatabaseUseCase: SaveNoteToDatabaseUseCase
) : BaseViewModel<MainEvent, HomeViewState>(Loading) {

    val viewState: StateFlow<HomeViewState> = _viewState

    private val _notesFlow: MutableSharedFlow<List<Note>> = MutableSharedFlow(replay = 1)
    val notesFlow: SharedFlow<List<Note>> = _notesFlow


    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _viewState.value) {
            is Loading -> currentState.reduce(event)
            is Error -> currentState.reduce(event)
            is FinishedLoading -> currentState.reduce(event)
            is NoItems -> currentState.reduce(event)
            is WaitingForInternet -> currentState.reduce(event)
        }
    }

    private fun Loading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            is MainEvent.EnterScreen -> {
                val isNetworkAvailable = event.networkStatus == Available
                // Loads notes from database or from server if database is empty and network is available
                loadNotes(isNetworkAvailable)
            }
            else -> Unit
        }
    }

    private fun Error.reduce(event: MainEvent) {
        logReduce(event)
    }

    private fun NoItems.reduce(event: MainEvent) {
        logReduce(event)
    }

    private fun FinishedLoading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            is MainEvent.CreateBlankNote -> {
                createBlankNote { note -> onObtainNote(note) }
            }
            is MainEvent.RemoveNote -> {
                removeNote(event.note)
            }
            is MainEvent.UpdateNote -> {
                updateNote(event.note)
            }

            else -> Unit
        }
    }

    private fun WaitingForInternet.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            is MainEvent.NetworkAvailable -> {
                loadNotes(networkAvailable = true)
            }
            else -> Unit
        }
    }

    /**
     * If database is empty, we load data from server. If we load data from server while we have data in database it's
     * unclear what we should display to user. Consequently, For test purposes we load data from server
     * only once(first app usage).
     * Since this app is build with MVVM, data is directly displayed to user and every change becomes visible immediately.
     * Thus far, if we load data from server, the user will see server data instead of database one.
     * The problem could be solved by uploading data to server, which can not be done in this case.
     */
    private fun loadNotes(networkAvailable: Boolean) {
        launch(
            logError = "Error getting notes from database",
            logSuccess = "Successfully received notes from database",
            onSuccess = { notes ->
                if (notes.isEmpty()) {
                    if (networkAvailable) {
                        loadNotesFromServer()
                    } else {
                        // First launch(database is empty and internet is not available)
                        _viewState.value = WaitingForInternet
                    }
                } else {
                    onObtainNotes(
                        notesList = notes,
                        isNetworkAvailable = networkAvailable,
                        saveToDatabase = false
                    )
                }
            },
            onFailure = {
                _viewState.value = Error(it)
            }
        ) {
            getNotesFromDatabaseUseCase()
        }
    }

    private fun loadNotesFromServer() {
        launch(
            logError = "Error getting notes from server",
            logSuccess = "Successfully received notes from server",
            onSuccess = { notes ->
                if (notes.isEmpty()) {
                    _viewState.value = NoItems
                } else
                    onObtainNotes(
                        notesList = notes,
                        isNetworkAvailable = true,
                        saveToDatabase = true
                    )
            },
            onFailure = {
                _viewState.value = Error(it)
            }
        ) {
            getNotesFromServerUseCase()
        }
    }


    /**
     * Puts the notes to notes flow and saves to the database if needed
     */
    private fun onObtainNotes(
        notesList: List<Note>,
        isNetworkAvailable: Boolean,
        saveToDatabase: Boolean
    ) {
        launch(
            onFailure = {
                _viewState.value = Error(it)
            }) {
            if (saveToDatabase) saveNotesToDatabaseUseCase(notesList)
            _notesFlow.emit(notesList)
            _viewState.value = FinishedLoading(notesList, isNetworkAvailable)
        }
    }


    /**
     * Removes the note from notes flow and database
     */
    private fun removeNote(note: Note) {
        launch(
            logSuccess = "Successfully removed note",
            logError = "Error while removing note",
            onFailure = { _viewState.value = Error(it) }
        ) {
            removeNoteFromDatabaseUseCase(note)

            val notes = _notesFlow.firstOrNull()?.toMutableList()
            notes?.apply {
                remove(note)
                _notesFlow.emit(this)
            }
        }
    }

    /**
     * Puts the note to notes flow and saves to the database
     */
    private fun onObtainNote(note: Note) {
        launch {
            val notes = _notesFlow.firstOrNull()?.toMutableList()
            notes?.apply {
                add(0, note)
                _notesFlow.emit(this)
            }

            saveNoteToDatabaseUseCase(note)
        }
    }

    private fun updateNote(note: Note) {
        launch {
            val notes = _notesFlow.firstOrNull()?.toMutableList()
            notes?.apply {
                notes.replaceAll { if (it.id == note.id) note else it }
                _notesFlow.emit(this)
            }

            saveNoteToDatabaseUseCase(note)
        }
    }
}