package com.cmdevs.projectunknown.util

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


fun AppCompatActivity.fragReplace(@IdRes id: Int, @LayoutRes fragment: Fragment, tag: String? = null) {
    supportFragmentManager
        .beginTransaction()
        .replace(id, fragment, tag)
        .commit()
}

fun AppCompatActivity.fragAdd(@IdRes id: Int, @LayoutRes fragment: Fragment, tag: String? = null) {
    supportFragmentManager
        .beginTransaction()
        .add(id, fragment, tag)
        .commit()
}