package com.alacrity.thenotes.ui.home.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.alacrity.thenotes.R
import com.alacrity.thenotes.util.internet.ConnectivityObserver
import com.alacrity.thenotes.util.internet.ConnectivityObserver.Status.*

@Composable
fun WaitingForInternetScreen(networkStatus: ConnectivityObserver.Status, onNetworkBecomeAvailable: () -> Unit) {
    if (networkStatus == Available) onNetworkBecomeAvailable()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(R.string.network_status, networkStatus.name), textAlign = TextAlign.Center)
    }
}