package com.cmdevs.projectunknown.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmdevs.projectunknown.domain.profile.ObserveProfileModifiedDateUseCase
import com.cmdevs.projectunknown.domain.profile.ObserveProfileUserUseCase

class ProfileViewModelFactory(
        val observeProfileUserUseCase: ObserveProfileUserUseCase,
        val observeProfileModifiedDateUseCase: ObserveProfileModifiedDateUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(observeProfileUserUseCase, observeProfileModifiedDateUseCase) as T
    }
}