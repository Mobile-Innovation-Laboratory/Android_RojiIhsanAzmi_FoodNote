package com.app.foodnote.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date?): String {
    if (date == null) return "-"
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(date)
}