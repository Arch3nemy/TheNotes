package com.alacrity.thenotes.utils

import java.text.SimpleDateFormat
import java.util.*

fun dateToProperString(date: Date): String {
    val hoursAndMinutesFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
    val daysMonthAndYearFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    val today = getCurrentDayOfMonth()
    val dateDay = date.getDayOfMonth()

    return if(today == dateDay) {
        hoursAndMinutesFormat.format(date)
    } else {
        daysMonthAndYearFormat.format(date)
    }
}

private fun getCurrentDayOfMonth(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.DAY_OF_MONTH)
}

private fun Date.getDayOfMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}