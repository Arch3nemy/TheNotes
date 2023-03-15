package com.alacrity.thenotes

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.alacrity.thenotes.ui.edit.EditViewModel
import com.alacrity.thenotes.ui.home.HomeViewModel
import com.alacrity.thenotes.util.WorkScheduler
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var editViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        setContent {
            TheNotesApp(
                homeViewModel = homeViewModel,
                editViewModel = editViewModel
            )
        }
        WorkScheduler().initUpdateDatesWork(this)
    }

}

