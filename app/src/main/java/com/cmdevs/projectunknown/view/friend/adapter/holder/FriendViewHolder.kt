package com.cmdevs.projectunknown.view.friend.adapter.holder

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.data.FriendListData
import kotlinx.android.synthetic.main.friend_item.view.*

class FriendViewHolder(context: Context, parent: ViewGroup?) :
    RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.friend_item, parent, false)) {

    fun onBind(item: FriendListData){
        itemView.onBind(item)
    }

    fun View.onBind(item: FriendListData){
        tv_name.text = item.name ?: ""
        tv_my_status_message.text = item.message ?: ""
    }

}