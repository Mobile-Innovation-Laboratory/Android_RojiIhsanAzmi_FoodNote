package com.app.foodnote.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.app.foodnote.utils.getCurrentTimeStampWithFormat
import com.app.foodnote.data.model.Note
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteFormBottomSheet(
    oldNote: Note = Note(),
    isEditing: Boolean = false,
    onSave: (Note) -> Unit,
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    var authorName by remember { mutableStateOf(oldNote.authorName ?: "") }
    var content by remember { mutableStateOf(oldNote.content ?: "") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (isEditing) "Edit Feedback" else "Add Feedback",
                style = MaterialTheme.typography.titleLarge
            )

            OutlinedTextField(
                value = authorName,
                onValueChange = { authorName = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Feedback") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                minLines = 3
            )

            Button(
                onClick = {
                    scope.launch {
                        onSave(oldNote.copy(
                            content = content,
                            authorName = authorName,
                            updatedAt = Date().getCurrentTimeStampWithFormat()
                        ))
                        sheetState.hide()
                        onDismiss()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isEditing) "Update" else "Add")
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}