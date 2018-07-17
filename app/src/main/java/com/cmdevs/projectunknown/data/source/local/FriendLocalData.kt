package com.cmdevs.projectunknown.data.source.local

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.data.FriendListData
import com.cmdevs.projectunknown.data.source.DataResource

class FriendLocalData : DataResource {

    override fun getFriendList(list: (LiveData<List<FriendListData>>) -> Unit) {
        val fakeLocalFriendList = mutableListOf<FriendListData>()
        fakeLocalFriendList.add(0, FriendListData("이치영", 28, "코딩은 어려웡"))
        fakeLocalFriendList.add(1, FriendListData("함명호", 29, "여자친구 구함"))
        fakeLocalFriendList.add(2, FriendListData("성홍규", 31, "감성터짐"))
        fakeLocalFriendList.add(3, FriendListData("고창재", 32, "노장"))
        fakeLocalFriendList.add(4, FriendListData("신대성", 30, "계란한판.."))

        val liveList = MutableLiveData<List<FriendListData>>()
        liveList.value = fakeLocalFriendList

        list(liveList)
    }

}