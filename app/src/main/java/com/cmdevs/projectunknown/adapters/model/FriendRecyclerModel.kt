package com.cmdevs.projectunknown.adapters.model

import com.cmdevs.projectunknown.data.Friend

interface FriendRecyclerModel{
    fun setItem(list: List<Friend>)
    fun getItem(position: Int): Friend
    fun getItemCount(): Int
}