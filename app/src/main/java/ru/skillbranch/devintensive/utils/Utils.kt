package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        //todo Fix Me!!!! Null or Empty value

        val parts: List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        //return Pair(firstName, lastName)
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