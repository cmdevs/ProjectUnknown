package com.cmdevs.projectunknown.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.cmdevs.projectunknown.adapters.FriendListAdapter
import com.cmdevs.projectunknown.data.FriendListData

@BindingAdapter("items")
fun setFriendList(view: RecyclerView, list: List<FriendListData>) {

    with(view) {
        (adapter as FriendListAdapter)?.let {
            it.setItem(list)
        }
    }

}