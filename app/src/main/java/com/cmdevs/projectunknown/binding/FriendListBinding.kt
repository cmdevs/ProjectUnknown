package com.cmdevs.projectunknown.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imgRes")
fun setProfileImage(view: ImageView, imgUrl: String) {
    with(view) {
        Glide.with(this)
            .load(imgUrl)
            .apply(RequestOptions().circleCrop().error(0))
            .into(this)
    }
}