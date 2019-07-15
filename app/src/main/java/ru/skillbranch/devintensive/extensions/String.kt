package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {

    var result = this.trim()

    return if (result.length < 3) result else "${result.substring(0, value).trim()}..."
}

fun String.stripHtml(): String = this.replace("\\<[^>]*>".toRegex(), "").replace("( )+".toRegex(), " ")


fun String.isDigitsOnly(): Boolean {
    val len = this.length
    for (i in 0 until len) {
        if (!Character.isDigit(this[i])) {
            return false
        }
    }
    return true
}

fun String.isAnyDigits(): Boolean {
    val len = this.length
    for (i in 0 until len) {
        if (Character.isDigit(this[i])) {
            return false
        }
    }
    return true
}