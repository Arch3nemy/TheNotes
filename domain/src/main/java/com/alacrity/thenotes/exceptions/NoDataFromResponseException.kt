package com.alacrity.thenotes.exceptions

class NoDataFromResponseException(message: String = "Undefined", exception: Throwable? = null) : TheNotesException(message, exception)