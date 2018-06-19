package com.cmdevs.projectunknown.util

import android.app.Activity

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


fun FragmentManager.fragReplace(@IdRes id: Int, fragment: Fragment, tag: String? = null){
    this.beginTransaction().replace(id, fragment, tag).commit()
}
