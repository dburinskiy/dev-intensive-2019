package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.absoluteValue

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String = SimpleDateFormat(pattern, Locale("ru")).format(this)

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    this.time += value * units.milliseconds
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val differenceInMilliseconds = this.time - date.time
    var ago = differenceInMilliseconds < 0

    return when (val seconds = TimeUnit.MILLISECONDS.toSeconds(differenceInMilliseconds.absoluteValue)) {
        in 0..1 -> "только что"

        in 1..45 -> agoOrFutureSuffix(ago, "несколько секунд")

        in 45..75 -> agoOrFutureSuffix(ago, "минуту")

        in 75..TimeUnit.MINUTES.toSeconds(45) -> agoOrFutureSuffix(
            ago, TimeUnits.MINUTE.plural(TimeUnit.SECONDS.toMinutes(seconds).toInt())
        )

        in TimeUnit.MINUTES.toSeconds(45)..TimeUnit.MINUTES.toSeconds(75) -> agoOrFutureSuffix(ago, "час")

        in TimeUnit.MINUTES.toSeconds(75)..TimeUnit.HOURS.toSeconds(22) -> agoOrFutureSuffix(
            ago, TimeUnits.HOUR.plural(TimeUnit.SECONDS.toHours(seconds).toInt())
        )

        in TimeUnit.HOURS.toSeconds(22)..TimeUnit.HOURS.toSeconds(26) -> agoOrFutureSuffix(ago, "день")

        in TimeUnit.HOURS.toSeconds(26)..TimeUnit.DAYS.toSeconds(360) -> agoOrFutureSuffix(
            ago, TimeUnits.DAY.plural(TimeUnit.SECONDS.toDays(seconds).toInt())
        )
        else -> if (ago) "более года назад" else "более чем через год"
    }

}

private fun agoOrFutureSuffix(ago: Boolean, text: String): String = if (ago) "${text} назад" else "через ${text}"

enum class TimeUnits(val milliseconds: Long, val titles: Array<String>) {

    SECOND(1000L, arrayOf("секунду", "секунды", "секунд")),
    MINUTE(SECOND.milliseconds * 60, arrayOf("минуту", "минуты", "минут")),
    HOUR(MINUTE.milliseconds * 60, arrayOf("час", "часа", "часов")),
    DAY(HOUR.milliseconds * 24, arrayOf("день", "дня", "дней"));

    //http://docs.translatehouse.org/projects/localization-guide/en/latest/l10n/pluralforms.html?id=l10n/pluralforms
    fun plural(value: Int): String {
        val titlesIndex = titlesIndex(value)
        return "$value ${this.titles[titlesIndex]}"
    }

    private fun titlesIndex(n: Int): Int {
        return if (n % 10 == 1 && n % 100 != 11) 0
        else if (n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20)) 1
        else 2
    }
}