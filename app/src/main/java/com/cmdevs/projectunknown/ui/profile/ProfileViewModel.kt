package com.cmdevs.projectunknown.ui.profile

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmdevs.projectunknown.data.UserAuthInfo
import com.cmdevs.projectunknown.domain.ObserveGettingStartedUseCase
import com.cmdevs.projectunknown.result.Event
import com.cmdevs.projectunknown.result.Result

class ProfileViewModel(
    val observeGettingStartedUseCase: ObserveGettingStartedUseCase
) : ViewModel() {

    val userEmail = MutableLiveData<String>()
    val userPhotoUrl = MutableLiveData<String>()
    val userDisplayName = MutableLiveData<String>()
    val isGettingStarted = MediatorLiveData<Event<Boolean>>()

    init {
        isGettingStarted.addSource(observeGettingStartedUseCase.observe()) {
            if ((it as? Result.Success)?.data != null) {
                isGettingStarted.postValue(
                    Event(it.data)
                )
            }
        }
    }

    lateinit var userAuthInfo: UserAuthInfo
    fun updateProfile(userAuthInfo: UserAuthInfo) {
        this@ProfileViewModel.userAuthInfo = userAuthInfo
        userEmail.postValue(this@ProfileViewModel.userAuthInfo.getEmail())
        userPhotoUrl.postValue(this@ProfileViewModel.userAuthInfo.getPhotoUrl())
        userDisplayName.postValue(this@ProfileViewModel.userAuthInfo.getDisplayName())
    }

    fun gettingStarted() {
        observeGettingStartedUseCase.execute(
            this@ProfileViewModel.userAuthInfo
        )
    }
}