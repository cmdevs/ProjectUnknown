package com.cmdevs.projectunknown.view.friend.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.cmdevs.projectunknown.data.FriendListData
import com.cmdevs.projectunknown.view.friend.adapter.holder.FriendViewHolder
import com.cmdevs.projectunknown.view.friend.adapter.model.FriendRecyclerModel

class FriendListAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(), FriendRecyclerModel{

    val itemList = mutableListOf<FriendListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = FriendViewHolder(context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FriendViewHolder)?.onBind(itemList[position])
    }

    override fun addItem(list: FriendListData) {
        itemList.add(list)
    }

    override fun getItem(position: Int) = itemList[position]

    override fun getItemCount() = itemList.size

    override fun notifyDataSetChange() {
        notifyDataSetChange()
    }

}