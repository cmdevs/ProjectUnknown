package com.cmdevs.projectunknown.view.friend.viewmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cmdevs.projectunknown.data.friend.FriendListRepository
import com.cmdevs.projectunknown.view.friend.adapter.model.FriendRecyclerModel

class FriendFactory(
    val repository: FriendListRepository,
    val adapterModel: FriendRecyclerModel,
    val lifecycle: Lifecycle
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FriendViewModel(repository, adapterModel, lifecycle) as T
    }

}

