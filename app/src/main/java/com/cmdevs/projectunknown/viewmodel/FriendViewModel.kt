package com.cmdevs.projectunknown.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import com.cmdevs.projectunknown.data.FriendListData
import com.cmdevs.projectunknown.data.source.DataRepository

class FriendViewModel(
    context: Application,
    repository: DataRepository,
    lifecycle: Lifecycle
) : AndroidViewModel(context), LifecycleObserver {

    private val friendList = MediatorLiveData<List<FriendListData>>()

    init {
        lifecycle.addObserver(this)

        repository.getFriendList {
            friendList.addSource(it, friendList::setValue)
        }
    }

    fun getFriends() = friendList

}