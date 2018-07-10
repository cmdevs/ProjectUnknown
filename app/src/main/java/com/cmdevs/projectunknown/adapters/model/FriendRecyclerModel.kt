package com.cmdevs.projectunknown.adapters.model

import com.cmdevs.projectunknown.data.FriendListData

interface FriendRecyclerModel{
    fun setItem(list: List<FriendListData>)
    fun getItem(position: Int): FriendListData
    fun getItemCount(): Int
    fun notifyDataSetChange()
}