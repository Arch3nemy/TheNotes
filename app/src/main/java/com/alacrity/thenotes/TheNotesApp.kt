package com.alacrity.thenotes

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.alacrity.thenotes.theme.AppTheme
import com.alacrity.thenotes.ui.main.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TheNotesApp(
    homeViewModel: MainViewModel
) {
    AppTheme {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
            }

            AppNavGraph(
                homeViewModel = homeViewModel,
            )
        }

}

