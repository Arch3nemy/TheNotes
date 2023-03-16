package com.alacrity.thenotes

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alacrity.thenotes.ui.edit.EditViewModel
import com.alacrity.thenotes.ui.home.HomeViewModel
import com.alacrity.thenotes.util.internet.ConnectivityObserver
import com.alacrity.thenotes.util.internet.NetworkConnectivityObserver
import com.alacrity.thenotes.util.workers.WorkScheduler
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var editViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContent {
            //Internet connection status
            val status by connectivityObserver.observe().collectAsState(
                initial = ConnectivityObserver.Status.Unavailable
            )
            TheNotesApp(
                homeViewModel = homeViewModel,
                editViewModel = editViewModel,
                networkStatus = status
            )
        }
    }

    private fun init() {
        App.appComponent.inject(this)
        WorkScheduler().initUpdateDatesWork(this)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
    }

}

