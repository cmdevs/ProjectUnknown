package com.cmdevs.projectunknown.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.util.Log
import com.cmdevs.projectunknown.data.Friend
import com.cmdevs.projectunknown.data.source.FriendRepository

class FriendViewModel(
    private val repository: FriendRepository
) : ViewModel() {

    private val friendList = LivePagedListBuilder<Int, Friend>(
        repository.getFriends(), PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(true)
            .build()
    ).build()

    private val newFriendEvent = MutableLiveData<Unit>()

    fun getFriends() = friendList

    fun getNewFriendEvent() = newFriendEvent

    fun addNewFriend() {
        newFriendEvent.value = null
    }

}

