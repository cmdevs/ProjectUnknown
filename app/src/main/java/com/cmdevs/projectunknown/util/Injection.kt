package com.cmdevs.projectunknown.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.cmdevs.projectunknown.ViewModelFactory
import com.cmdevs.projectunknown.data.source.DataRepository

object Injection {
    fun provideDataRepository() = DataRepository
}


fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.newInstance(application, lifecycle)).get(viewModelClass)



