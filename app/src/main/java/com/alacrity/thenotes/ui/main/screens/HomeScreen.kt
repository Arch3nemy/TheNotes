package com.alacrity.thenotes.ui.main.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alacrity.thenotes.R
import com.alacrity.thenotes.entity.Note

@Composable
fun HomeScreen(notesList: List<Note>, onFabClick: () -> Unit) {
    Scaffold(
        topBar = { },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onFabClick = onFabClick) },
        content = { paddingValues -> Screen(paddingValues, notesList) })
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

@Composable
fun Screen(paddingValues: PaddingValues, notesList: List<Note>) {
    Box(modifier = Modifier.padding(paddingValues)) {
        LazyColumn {
            items(notesList) { note ->
                NoteView(note = note)
                Divider(color = Color.Transparent, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun NoteView(modifier: Modifier = Modifier, note: Note) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.Cyan
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TitleAndDescription(modifier = Modifier, title = note.title, description = note.description)
            Text(text = note.date.toString())
        }
    }

}

@Composable
fun TitleAndDescription(modifier: Modifier = Modifier, title: String, description: String) {
    Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
        Text(modifier = Modifier.fillMaxWidth(), text = title, textAlign = TextAlign.Center)
        Text(modifier = Modifier.fillMaxWidth(), text = description, textAlign = TextAlign.Center)
    }
}


