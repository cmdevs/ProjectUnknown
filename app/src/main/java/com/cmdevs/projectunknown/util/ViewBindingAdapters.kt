package com.cmdevs.projectunknown.util

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    Log.d("cylee","goneUnless visible : ${visible}")
    view.visibility = if (visible) View.VISIBLE else View.GONE
}