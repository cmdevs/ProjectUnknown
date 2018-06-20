package com.cmdevs.projectunknown.util

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replace(@IdRes id: Int, fragment: Fragment, tag: String? = null) {
    supportFragmentManager
        .beginTransaction()
        .replace(id, fragment, tag)
        .commit()
}