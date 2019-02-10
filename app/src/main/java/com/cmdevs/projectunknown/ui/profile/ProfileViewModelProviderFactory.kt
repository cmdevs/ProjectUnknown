package com.cmdevs.projectunknown.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmdevs.projectunknown.domain.ObserveGettingStartedUseCase

class ProfileViewModelProviderFactory(
    val observeGettingStartedUseCase: ObserveGettingStartedUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(
            observeGettingStartedUseCase
        )
        else -> super.create(modelClass)
    } as T
}
