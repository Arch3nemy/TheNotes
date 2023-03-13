package com.alacrity.thenotes.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.alacrity.thenotes.App
import com.alacrity.thenotes.TheNotesApp
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        setContent {
            TheNotesApp(homeViewModel = mainViewModel)
        }
    }

}