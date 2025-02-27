package com.app.foodnote.widgets

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.app.foodnote.data.local.entity.Schedule
import com.app.foodnote.utils.formatDate
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScheduleDialog(
    modifier: Modifier = Modifier,
    schedule: Schedule = Schedule(),
    onDismiss: () -> Unit,
    onSave: (Schedule) -> Unit,
) {
    var scheduleName by remember { mutableStateOf(schedule.title ?: "") }
    var scheduleDate by remember { mutableStateOf(schedule.date) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Jika schedule memiliki tanggal, set kalender ke tanggal tersebut
    scheduleDate?.let {
        calendar.time = it
    }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                scheduleDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }.time
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    BasicAlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        content = {
            Surface(
                modifier = Modifier.wrapContentWidth().wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "Add/Edit Schedule")

                    TextField(
                        value = scheduleName,
                        onValueChange = { scheduleName = it },
                        placeholder = { Text(text = "Title") }
                    )

                    // Ganti TextField dengan Button untuk memilih tanggal
                    Button(
                        onClick = { datePickerDialog.show() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = scheduleDate?.let { formatDate(it) } ?: "Select Date")
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Button(onClick = {
                            onSave(
                                schedule.copy(
                                    title = scheduleName,
                                    date = scheduleDate
                                )
                            )
                            onDismiss()
                        }) {
                            Text(text = "Save")
                        }
                    }
                }
            }
        }
    )
}