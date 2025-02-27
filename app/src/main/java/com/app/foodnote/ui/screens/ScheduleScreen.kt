package com.app.foodnote.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.app.foodnote.data.local.database.ScheduleDatabase
import com.app.foodnote.data.local.entity.Schedule
import com.app.foodnote.data.repository.ScheduleRepository
import com.app.foodnote.widgets.AddScheduleDialog
import com.app.foodnote.widgets.ScheduleItem
import kotlinx.coroutines.launch

@Composable
fun ScheduleContent() {
    var openDialog by remember { mutableStateOf(false) }
    var selectedSchedule by remember { mutableStateOf(Schedule()) }
    var isEdit by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val database = remember { ScheduleDatabase.getDatabase(context) }
    val scheduleRepository = remember { ScheduleRepository.getInstance(database.scheduleDao()) }

    val scope = rememberCoroutineScope()

    val schedules by scheduleRepository.getSchedules().observeAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                isEdit = false
                openDialog = true
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add schedule"
                )
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            if (openDialog) {
                AddScheduleDialog(
                    schedule = selectedSchedule,
                    onDismiss = {
                        selectedSchedule = Schedule()
                        openDialog = false
                    },
                    onSave = { schedule ->
                        if (isEdit) {
                            scope.launch {
                                scheduleRepository.updateSchedule(schedule)
                            }
                            openDialog = false
                        } else {
                            scope.launch {
                                scheduleRepository.insertSchedule(schedule)
                            }
                            openDialog = false
                        }

                    }
                )
            }


            // Content
            if (schedules.isEmpty()) {
                Text(
                    text = "No schedules available ",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            } else {
                LazyColumn {
                    // Load all Schedule
                    items(schedules) { schedule ->
                        ScheduleItem(
                            schedule = schedule,
                            onUpdate = {
                                selectedSchedule = schedule
                                openDialog = true
                                isEdit = true
                            },
                            onDelete = {
                                scope.launch {
                                    scheduleRepository.deleteSchedule(schedule)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}