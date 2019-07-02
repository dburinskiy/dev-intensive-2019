package ru.skillbranch.devintensive.utils

object Utils {

    val charsMap: Map<String, String> = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trimIndent()?.ifEmpty { null }?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return ("${firstName?.capitalize()?.take(1)?.getOrNull(0) ?: ""}" +
                "${lastName?.capitalize()?.take(1)?.getOrNull(0) ?: ""}").trimIndent().ifEmpty { null }
    }

    fun transliteration(fullName: String, divider: String = " "): String {

        return fullName.split(" ").joinToString(separator = divider) { name -> transliterationOneWord(name) }
    }

    private fun transliterationOneWord(word: String): String {
        return word.trimIndent().toCharArray().joinToString(separator = "",
            transform = { c -> transliterationOneChar(c) })
    }

    private fun transliterationOneChar(letter: Char): String {
        val isUpperCase = letter.isUpperCase()
        var result = letter.toLowerCase().toString()

        if (charsMap.containsKey(result))
            result = charsMap.getValue(result).toString()

        if (isUpperCase) {
            result = result.capitalize()
        }
        return result

    }

}