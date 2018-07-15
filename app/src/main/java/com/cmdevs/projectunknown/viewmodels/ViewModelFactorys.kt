package com.cmdevs.projectunknown.viewmodels

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.cmdevs.projectunknown.data.source.FriendRepository
import com.cmdevs.projectunknown.util.Injection

class ViewModelFactorys(
    private val context: Context
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var INSTANCE: ViewModelFactorys? = null

        fun newInstance(context: Context) =
            INSTANCE ?: synchronized(
                ViewModelFactorys::class.java
            ) {
                INSTANCE ?: ViewModelFactorys(context)
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(FriendViewModel::class.java) -> FriendViewModel(Injection.getFriendRepository(context))

                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

}