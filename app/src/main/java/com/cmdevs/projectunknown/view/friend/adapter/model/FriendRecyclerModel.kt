package com.cmdevs.projectunknown.view.friend.adapter.model

import com.cmdevs.projectunknown.data.FriendListData

interface FriendRecyclerModel{

    fun addItem(list: FriendListData)
    fun getItem(position: Int): FriendListData
    fun getItemCount(): Int
    fun notifyDataSetChange()

}