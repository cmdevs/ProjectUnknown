package com.cmdevs.projectunknown.view.friend.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.cmdevs.projectunknown.view.friend.adapter.holder.FriendViewViewHolder

class FriendListAdapter(
    private val context: Context?
) : BaseRecyclerAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        FriendViewViewHolder(context, parent)

}