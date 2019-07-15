package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    view?.let { v ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.let { it.hideSoftInputFromWindow(v.windowToken, 0) }
    }
}

fun Activity.isKeyboardOpen(): Boolean {
    val SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128

    val rootView = window.decorView.rootView
    var rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect);


    return rootView.bottom - rect.bottom >
            SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * rootView.resources.displayMetrics.density;

}

fun Activity.isKeyboardClosed(): Boolean {
    return !isKeyboardOpen()
}
