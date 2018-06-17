package com.cmdevs.projectunknown.data.friend

import com.cmdevs.projectunknown.data.FriendListData

class FriendListLocalData : FriendListDataResource {

    override fun getFriendList(list: (List<FriendListData>) -> Unit) {
        val localList = mutableListOf<FriendListData>()

        localList.add(0, FriendListData("이치영",28, "코딩은 어려웡"))
        localList.add(1, FriendListData("함명호",29, "여자친구 구함"))
        localList.add(2, FriendListData("성홍규",31, "감성터짐"))
        localList.add(3, FriendListData("고창재",32, "노장"))
        localList.add(4, FriendListData("신대성",30, "계란한판.."))

        list(localList)
    }

}