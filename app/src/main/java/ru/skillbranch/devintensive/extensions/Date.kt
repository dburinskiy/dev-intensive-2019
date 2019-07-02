package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String = SimpleDateFormat(pattern, Locale("ru")).format(this)

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    this.time += value * units.milliseconds
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    return ""
}

enum class TimeUnits(val milliseconds: Long) {
    SECOND(1000L),
    MINUTE(SECOND.milliseconds * 60),
    HOUR(MINUTE.milliseconds * 60),
    DAY(HOUR.milliseconds * 24)
}