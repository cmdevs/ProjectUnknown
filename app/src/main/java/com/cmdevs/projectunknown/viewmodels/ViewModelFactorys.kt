package com.cmdevs.projectunknown.viewmodels

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cmdevs.projectunknown.util.Injection

class ViewModelFactorys(
    private val context: Application
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var INSTANCE: ViewModelFactorys? = null

        fun newInstance(context: Application) =
            INSTANCE ?: synchronized(
                ViewModelFactorys::class.java
            ) {
                INSTANCE ?: ViewModelFactorys(context)
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom((ProfileViewModel::class.java)) -> ProfileViewModel(Injection.getLoginRepository(context))
                isAssignableFrom(FriendViewModel::class.java) -> FriendViewModel(Injection.getFriendRepository(context))
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

}
