package com.cmdevs.projectunknown.view.friend.adapter

import android.support.v7.widget.RecyclerView
import com.cmdevs.projectunknown.data.FriendListData
import com.cmdevs.projectunknown.view.friend.adapter.holder.FriendViewViewHolder
import com.cmdevs.projectunknown.view.friend.adapter.model.FriendRecyclerModel

abstract class BaseRecyclerAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), FriendRecyclerModel {

    val itemList = mutableListOf<FriendListData>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FriendViewViewHolder)?.onCreateViewHolder(itemList[position])
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
