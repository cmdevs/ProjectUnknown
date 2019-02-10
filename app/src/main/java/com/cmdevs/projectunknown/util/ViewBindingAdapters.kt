package com.cmdevs.projectunknown.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    Log.d("cylee", "goneUnless visible : ${visible}")
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("imgRes")
fun loadImageUrl(view: ImageView, imageUrl: String?) {
    with(view) {
        Glide.with(this)
            .load(imageUrl)
            .apply(RequestOptions().circleCrop().error(0))
            .into(this)
    }
}
