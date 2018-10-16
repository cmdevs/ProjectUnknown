package com.cmdevs.projectunknown.util

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.cmdevs.projectunknown.App
import com.cmdevs.projectunknown.data.source.FriendRepository
import com.cmdevs.projectunknown.data.source.LoginRepository
import com.cmdevs.projectunknown.data.source.local.AppDatabase
import com.cmdevs.projectunknown.viewmodels.FriendListViewModelFactory
import com.cmdevs.projectunknown.viewmodels.ViewModelFactorys

object Injection {
    fun getFriendRepository(context: Application) = FriendRepository(AppDatabase.getInstance(context).friendDao())
    fun getLoginRepository(context: Application) = LoginRepository(AppDatabase.getInstance(context).loginDao())

    /*fun provideFriendListViewModelFactory(context: Context) =
        FriendListViewModelFactory(getFriendRepository(context))*/
}

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>): T {
    return ViewModelProviders.of(
        this, ViewModelFactorys.newInstance(
            application
        )
    )
        .get(viewModelClass)
}

/*inline fun <reified T : ViewModel> Fragment.obtainViewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> AppCompatActivity.obtainViewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}*/

inline fun <reified T : ViewModel> Fragment.obtainViewModel(factory: ViewModelProvider.Factory): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    return vm
}

inline fun <reified T : ViewModel> AppCompatActivity.obtainViewModel(factory: ViewModelProvider.Factory): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    return vm
}



