package com.cmdevs.projectunknown.view.friend.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<in ITEM : Any?>(
    private val context: Context?,
    private val view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun onCreateViewHolder(item: ITEM)
}