package com.alacrity.thenotes

interface EventHandler<T> {
    fun obtainEvent(event: T)
}