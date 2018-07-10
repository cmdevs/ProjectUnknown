package com.cmdevs.projectunknown.adapters.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<in ITEM: Any?>(view: View?) : RecyclerView.ViewHolder(view), LayoutContainer {

    abstract fun onCreateViewHolder(item: ITEM)
}
