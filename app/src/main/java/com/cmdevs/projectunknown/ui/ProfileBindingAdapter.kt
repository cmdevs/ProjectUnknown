package com.cmdevs.projectunknown.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

//@BindingAdapter(value={"imageUrl"}, requireAll=false)
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    with(view) {
        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(this)
    }
}