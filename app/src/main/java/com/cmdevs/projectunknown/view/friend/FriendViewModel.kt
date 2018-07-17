package com.cmdevs.projectunknown.view.friend.viewmodel

import android.arch.lifecycle.*
import android.util.Log
import com.cmdevs.projectunknown.data.FriendListData
import com.cmdevs.projectunknown.data.friend.FriendListRepository
import com.cmdevs.projectunknown.view.friend.adapter.model.FriendRecyclerModel

class FriendViewModel(
    private val friendListRepository: FriendListRepository,
    private val firendRecyclerModel: FriendRecyclerModel,
    private val lifeCycle: Lifecycle
) : ViewModel(), LifecycleObserver {

    val friendDatas: MutableLiveData<FriendListData> = MutableLiveData()

    init {
        //friendDatas = MutableLiveData()
        lifeCycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        getFriendList()
    }

    fun getFriendList() {
        friendListRepository.getFriendList {
            it?.let {
                friendDatas.value = it.get(0)
            }
        }
    }

}