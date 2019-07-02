package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trimIndent()?.ifEmpty { null }?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(fullName: String, divider: String = " "): String {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return ""
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        //     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return ""
    }
}