package com.cmdevs.projectunknown.data.source

import android.arch.lifecycle.LiveData
import com.cmdevs.projectunknown.data.FriendListData

interface DataResource{

    //친구 리스트 가져오기.
    fun getFriendList(list: (LiveData<List<FriendListData>>) -> Unit)

}