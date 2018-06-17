package com.cmdevs.projectunknown.data.friend

import com.cmdevs.projectunknown.data.FriendListData

interface FriendListDataResource{

    //친구 리스트 가져오기.
    fun getFriendList(list: (List<FriendListData>) -> Unit)
}