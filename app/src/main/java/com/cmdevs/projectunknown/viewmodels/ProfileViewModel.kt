package com.cmdevs.projectunknown.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.cmdevs.projectunknown.data.Profile
import com.cmdevs.projectunknown.data.source.LoginRepository

class ProfileViewModel
    (private val repository: LoginRepository) : ViewModel() {

    val profile = MutableLiveData<Profile>()

    val name = MutableLiveData<String>()
    val photoUrl = MutableLiveData<String>()

    fun getLiveProfile() = profile
}
