package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    view?.let { v ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.let { it.hideSoftInputFromWindow(v.windowToken, 0) }
    }
}

fun Activity.isKeyboardOpen(): Boolean {
    //rootView.getWindowVisibleDisplayFrame(Rect())
    return false
}

fun Activity.isKeyboardClosed(): Boolean {
    return false
}
