package com.cmdevs.projectunknown.data.source

import android.arch.lifecycle.LiveData
import com.cmdevs.projectunknown.data.FriendListData
import com.cmdevs.projectunknown.data.source.local.FriendLocalData

object DataRepository : DataResource {

    val friendListLocalData: FriendLocalData by lazy {
        FriendLocalData()
    }

    override fun getFriendList(list: (LiveData<List<FriendListData>>) -> Unit) {
        friendListLocalData.getFriendList(list)
    }

}