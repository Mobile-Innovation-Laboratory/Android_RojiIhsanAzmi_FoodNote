package com.app.foodnote.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.foodnote.AuthViewModel
import kotlinx.coroutines.launch
import com.app.foodnote.utils.getCurrentTimeStampWithFormat
import com.app.foodnote.data.model.Note
import com.app.foodnote.data.repository.NotesRepository
import com.app.foodnote.ui.viewmodel.RecipeViewModel
import com.app.foodnote.widgets.NoteFormBottomSheet
import com.app.foodnote.widgets.NoteItem
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackContent(navController: NavController? = null, authViewModel: AuthViewModel, recipeViewModel: RecipeViewModel){
    var notes by remember { mutableStateOf(listOf<Note>()) }
    var selectedNotes = remember { Note() }
    var errorMessage by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    val notesRepository = remember { NotesRepository() }

    val scope = rememberCoroutineScope()

    suspend fun loadAllData() {
        selectedNotes = Note()
        notesRepository.getAllNotes().onSuccess { value ->
            notes = value
        }.onFailure { exception ->
            errorMessage = exception.message ?: "error"
        }
    }

    LaunchedEffect(Unit) {
        scope.launch {
            loadAllData()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Feedbacks") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isEditing = false
                    showBottomSheet = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Feedbacks"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Error Message
            if (errorMessage.isNotBlank()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Notes List
            LazyColumn {
                // Load all notes
                items(notes) { note ->
                    NoteItem(
                        note = note,
                        onUpdate = {
                            scope.launch {
                                selectedNotes = note
                                isEditing = true
                                showBottomSheet = true
                            }
                        }
                    )
                }
            }
        }

        if (showBottomSheet) {
            NoteFormBottomSheet(
                oldNote = selectedNotes,
                isEditing = isEditing,
                onSave = { note ->
                    scope.launch {
                        if (isEditing) {
                            notesRepository.updateNoteWithPut(note).onSuccess { value ->
                                loadAllData()
                            }.onFailure { exception ->
                                errorMessage = exception.message ?: "error"
                            }
                        } else {
                            notesRepository.createNote(
                                note.copy(
                                    createdAt = Date().getCurrentTimeStampWithFormat()
                                )
                            ).onSuccess { value ->
                                loadAllData()
                            }.onFailure { exception ->
                                errorMessage = exception.message ?: "error"
                            }
                        }
                    }
                },
                onDismiss = {
                    showBottomSheet = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeedbackContent() {
    FeedbackContent(navController = null, authViewModel = AuthViewModel(), recipeViewModel = RecipeViewModel())
}