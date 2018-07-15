package com.cmdevs.projectunknown.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.cmdevs.projectunknown.data.source.FriendRepository
import com.cmdevs.projectunknown.data.source.local.AppDatabase
import com.cmdevs.projectunknown.viewmodels.FriendListViewModelFactory
import com.cmdevs.projectunknown.viewmodels.ViewModelFactorys

object Injection {
    fun getFriendRepository(context: Context) =
        FriendRepository(AppDatabase.getInstance(context).friendDao())

    fun provideFriendListViewModelFactory(context: Context) =
        FriendListViewModelFactory(getFriendRepository(context))
}

//Refectoring
fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>): T {
    return ViewModelProviders.of(
        this, ViewModelFactorys.newInstance(
            this
        )
    )
        .get(viewModelClass)
}



