package com.alacrity.thenotes.entity

import java.util.Date

data class Note(
    val id: Int,
    val title: String,
    val description: String,
    val date: Date
)