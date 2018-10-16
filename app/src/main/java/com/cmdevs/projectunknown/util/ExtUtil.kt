package com.cmdevs.projectunknown.util

import android.content.Context
import android.support.v7.app.AlertDialog
import com.cmdevs.projectunknown.R
import java.lang.Exception

fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

fun loginException(exception: Exception, block: (Exception) -> Unit) {
    block(exception)
}

fun makeLoadingDialog(context: Context) = AlertDialog.Builder(
    context,
    R.style.Theme_TransparentBackground
).apply { setView(R.layout.dialog_loading) }.create()
