package com.alacrity.thenotes.ui.edit.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alacrity.thenotes.R
import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.room.NoteTableItem
import com.alacrity.thenotes.theme.ToolbarColor
import com.alacrity.thenotes.theme.lightSecondary
import com.alacrity.thenotes.util.generateCurrentDate
import com.alacrity.thenotes.utils.toNote

@Composable
fun EditScreen(note: NoteTableItem, onBackAction: () -> Unit, onSaveClick: (Note) -> Unit) {
    val oldTitle = note.title
    val oldDescription = note.description

    var title by remember { mutableStateOf(note.title) }
    var description by remember { mutableStateOf(note.description) }
    var showSaveButton by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                date = note.date,
                showSaveButton = showSaveButton,
                onBackToUsersScreen = { onBackAction() },
                onSaveClick = {
                    onSaveClick(
                        note.copy(
                            title = title,
                            description = description,
                            date = if (oldTitle != title || oldDescription != description) generateCurrentDate() else note.date
                        ).toNote()
                    )
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TitleAndDescription(
                title = note.title,
                description = note.description,
                onTitleChanged = {
                    if (title != it) {
                        showSaveButton = true
                    }
                    title = it
                },
                onDescriptionChanged = {
                    if (description != it) {
                        showSaveButton = true
                    }
                    description = it
                }
            )
        }
    }
}

@Composable
fun TitleAndDescription(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Divider(modifier = Modifier.height(10.dp), color = Color.Transparent)

        TitleDescriptionCard(
            defaultText = title,
            onValueChanged = { text -> onTitleChanged(text) },
            label = stringResource(R.string.title_goes_here)
        )

        Divider(modifier = Modifier.height(10.dp), color = Color.Transparent)

        TitleDescriptionCard(
            defaultText = description,
            onValueChanged = { text -> onDescriptionChanged(text) },
            label = stringResource(R.string.desc_goes_here)
        )
    }
}

@Composable
fun TopAppBar(
    date: String,
    showSaveButton: Boolean,
    onBackToUsersScreen: () -> Unit,
    onSaveClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                text = date,
                textAlign = TextAlign.End
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackToUsersScreen() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        },
        actions = {
            if (showSaveButton)
                Button(onClick = { onSaveClick() }) {
                    Text(text = stringResource(R.string.save))
                }
        },
        backgroundColor = ToolbarColor
    )
}

@Composable
fun TitleDescriptionCard(defaultText: String, onValueChanged: (String) -> Unit, label: String) {
    var text by remember { mutableStateOf(TextFieldValue(defaultText)) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = lightSecondary
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
                onValueChanged(it.text)
            },
            label = { Text(text = label) }
        )
    }
}

