package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    view?.let { v ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.let { it.hideSoftInputFromWindow(v.windowToken, 0) }
    }
}

fun Activity.isKeyboardOpen(): Boolean {
    val SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128;
    val rootView = getWindow().getDecorView().getRootView()
    var r:Rect = Rect();
    rootView.getWindowVisibleDisplayFrame(r);
    val dm:DisplayMetrics = rootView.getResources().getDisplayMetrics();
    /* heightDiff = rootView height - status bar height (r.top) - visible frame height (r.bottom - r.top) */
    var heightDiff = rootView.getBottom() - r.bottom;
    /* Threshold size: dp to pixels, multiply with display density */
    var isKeyboardShown = heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density;

//    Log.d(TAG, "isKeyboardShown ? " + isKeyboardShown + ", heightDiff:" + heightDiff + ", density:" + dm.density
//            + "root view height:" + rootView.getHeight() + ", rect:" + r);

    return isKeyboardShown;
    //return false
}

fun Activity.isKeyboardClosed(): Boolean {
    return !isKeyboardOpen()
}
