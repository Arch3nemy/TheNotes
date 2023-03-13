package com.alacrity.thenotes.exceptions

open class TheNotesException(message: String? = "Undefined Error", exception: Throwable? = null): Exception(message, exception)