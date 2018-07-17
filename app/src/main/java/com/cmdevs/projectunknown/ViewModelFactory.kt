package com.cmdevs.projectunknown

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cmdevs.projectunknown.data.source.DataRepository
import com.cmdevs.projectunknown.util.Injection
import com.cmdevs.projectunknown.viewmodel.FriendViewModel

class ViewModelFactory(
    private val application: Application,
    private val repository: DataRepository,
    private val lifecycle: Lifecycle
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun newInstance(application: Application, lifecycle: Lifecycle) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(application, Injection.provideDataRepository(), lifecycle)
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(FriendViewModel::class.java) ->
                    FriendViewModel(application, repository, lifecycle)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

}