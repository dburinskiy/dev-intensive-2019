package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECONDS = 1000L
const val MINUTE = 60 * SECONDS
const val HOUR = 60 * MINUTE
const val DAY = 60 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: String): Date {
    var time = this.time

    time += when (units) {
        "second", "seconds" -> value * SECONDS
        "minute", "minutes" -> value * MINUTE
        "hour", "hours" -> value * HOUR
        "day", "days" -> value * DAY
        else -> throw IllegalStateException("invalid units")
    }
    this.time = time
    return this
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECONDS
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}


enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}