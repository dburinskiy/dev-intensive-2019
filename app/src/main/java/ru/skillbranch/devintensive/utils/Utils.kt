package ru.skillbranch.devintensive.utils

object Utils {

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
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return ""
    }

}