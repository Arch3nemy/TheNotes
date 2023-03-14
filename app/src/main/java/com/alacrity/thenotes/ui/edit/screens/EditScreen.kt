package com.alacrity.thenotes.ui.edit.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.alacrity.thenotes.R
import com.alacrity.thenotes.room.NoteTableItem
import com.alacrity.thenotes.theme.GradientColor2

@Composable
fun EditScreen(note: NoteTableItem, onBackAction: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar { onBackAction() } }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = note.title, textAlign = TextAlign.Center)
            Text(text = note.description, textAlign = TextAlign.Center)
            Text(text = note.date, textAlign = TextAlign.Center)
        }
    }

}

@Composable
fun TopAppBar(onBackToUsersScreen: () -> Unit) {
    androidx.compose.material.TopAppBar(
        title = { Text(stringResource(R.string.edit_page)) },
        navigationIcon = {
            IconButton(onClick = { onBackToUsersScreen() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        },
        backgroundColor = GradientColor2
    )
}

