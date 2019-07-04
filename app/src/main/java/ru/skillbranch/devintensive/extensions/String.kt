package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {

    var result = this.trim()

    return if (result.length < 3) result else "${result.substring(0, value).trim()}..."
}
