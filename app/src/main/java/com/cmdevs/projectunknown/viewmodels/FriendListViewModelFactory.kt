package com.cmdevs.projectunknown.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cmdevs.projectunknown.data.source.FriendRepository

class FriendListViewModelFactory(
    private val repository: FriendRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        FriendViewModel(repository) as T

}