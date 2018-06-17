package com.cmdevs.projectunknown.data.friend

import com.cmdevs.projectunknown.data.FriendListData

object FriendListRepository : FriendListDataResource{

    val friendListLocalData: FriendListLocalData by lazy {
        FriendListLocalData()
    }

    override fun getFriendList(list: (List<FriendListData>) -> Unit) {
        friendListLocalData.getFriendList(list)
    }

}