package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

fun User.toUserView(): UserView {
    val nickname = Utils.transliteration("$firstName $lastName")
    val status =
        if (lastVisit == null) "Еще не раз не был" else if (isOnline) "online" else "Последний раз был ${lastVisit?.humanizeDiff()}"
    val initials = Utils.toInitials(firstName, lastName)

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickname,
        avatar = avatar,
        status = status,
        initials = initials
    )
}

private fun Date.humanizeDiff(date: Date = Date()): String {
    //todo
    return ""
}