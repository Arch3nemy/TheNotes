package com.alacrity.thenotes.ui.home.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.alacrity.thenotes.R
import com.alacrity.thenotes.entity.Note
import com.alacrity.thenotes.theme.ToolbarColor
import com.alacrity.thenotes.theme.primaryLight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    notesList: List<Note>,
    isNetworkAvailable: Boolean,
    onFabClick: () -> Unit,
    onRemoveNote: (Note) -> Unit,
    onNoteClick: (Note) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar() },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onFabClick = onFabClick) },
        content = { paddingValues ->
            Screen(
                paddingValues,
                isNetworkAvailable,
                notesList,
                onRemoveNote
            ) { onNoteClick(it) }
        })
}

@Composable
fun FloatingActionButton(modifier: Modifier = Modifier, onFabClick: () -> Unit) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { onFabClick() }
    ) {
        Icon(Icons.Filled.Add, stringResource(R.string.new_note_button))
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun Screen(
    paddingValues: PaddingValues,
    isNetworkAvailable: Boolean,
    notesList: List<Note>,
    onRemoveNote: (Note) -> Unit,
    onNoteClick: (Note) -> Unit
) {
    var snackbarVisibleState by remember { mutableStateOf(!isNetworkAvailable) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(paddingValues).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn {
            items(
                items = notesList,
                key = { note -> note.id },
                itemContent = { note ->
                    val currentItem by rememberUpdatedState(note)
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                                onRemoveNote(currentItem)
                                true
                            } else false
                        }
                    )

                    if (dismissState.isDismissed(DismissDirection.EndToStart) ||
                        dismissState.isDismissed(DismissDirection.StartToEnd)
                    ) {
                        onRemoveNote(note)
                    }

                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier
                            .padding(vertical = 1.dp)
                            .animateItemPlacement(),
                        directions = setOf(
                            DismissDirection.StartToEnd,
                            DismissDirection.EndToStart
                        ),
                        dismissThresholds = { direction ->
                            FractionalThreshold(
                                if (direction == DismissDirection.StartToEnd) 0.66f else 0.50f
                            )
                        },
                        background = {},
                        dismissContent = {
                            NoteView(note = note) {
                                onNoteClick(note)
                            }
                            Divider(color = Color.Transparent, thickness = 1.dp)
                        }
                    )
                })
        }
        if (snackbarVisibleState) {
            Snackbar(
                modifier = Modifier.padding(8.dp)
            ) { Text(text = stringResource(id = R.string.network_is_not_available)) }
        }
    }


    LaunchedEffect(key1 = paddingValues) {
        coroutineScope.launch {
            delay(3000)
            snackbarVisibleState = false
        }
    }

}

@Composable
fun NoteView(modifier: Modifier = Modifier, note: Note, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(10.dp),
        backgroundColor = primaryLight
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TitleAndDescription(
                modifier = Modifier.weight(7.5f),
                title = note.title,
                description = note.description
            )
            Text(
                modifier = Modifier
                    .weight(2.5f)
                    .padding(end = 10.dp),
                text = note.date,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun TitleAndDescription(modifier: Modifier = Modifier, title: String, description: String) {
    Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
        TitleAndDescriptionTextField(title)
        TitleAndDescriptionTextField(description)
    }
}

@Composable
fun TitleAndDescriptionTextField(text: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun TopAppBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.home_page)) },
        backgroundColor = ToolbarColor
    )
}


