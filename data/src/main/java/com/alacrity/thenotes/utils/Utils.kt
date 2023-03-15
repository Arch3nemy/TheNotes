package com.alacrity.thenotes.utils

import com.alacrity.thenotes.entity.Note
import java.text.SimpleDateFormat
import java.util.*

fun dateToProperString(date: Date): String {
    val hoursAndMinutesFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
    val daysMonthAndYearFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    val today = getCurrentDayOfMonth()
    val dateDay = date.getDayOfMonth()

    return if (today == dateDay) {
        hoursAndMinutesFormat.format(date)
    } else {
        daysMonthAndYearFormat.format(date)
    }
}

/**
 * get day month and year of date that was 1 hour ago
 */
fun getUpdatedNoteDayMonthAndYear(): String {
    val daysMonthAndYearFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return daysMonthAndYearFormat.format(Date().toInstant().minusMillis(3600000))
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

fun getCurrentHourOfDay(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    return calendar.get(Calendar.HOUR_OF_DAY)
}