package com.cmdevs.projectunknown.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmdevs.projectunknown.data.ProfileData
import com.cmdevs.projectunknown.domain.profile.ObserveProfileModifiedDateUseCase
import com.cmdevs.projectunknown.domain.profile.ObserveProfileUserUseCase
import com.cmdevs.projectunknown.domain.result.Event
import com.cmdevs.projectunknown.domain.result.Result
import com.cmdevs.projectunknown.util.map

class ProfileViewModel(
        val observeProfileUserUseCase: ObserveProfileUserUseCase,
        val observeProfileModifiedDateUseCase: ObserveProfileModifiedDateUseCase
) : ViewModel() {

    val profileUserObservable: MediatorLiveData<Result<ProfileData>>
    var profileData: LiveData<ProfileData?>
    var uid: String? = null
    val startObservable = MutableLiveData<Event<Boolean>>()

    init {
        profileUserObservable = observeProfileUserUseCase.observe()
        profileData = profileUserObservable.map {
            (it as? Result.Success)?.data
        }
    }

    /**
     * 프로파일 업데이트
     **/
    fun updateProfile(uid: String?) {
        uid?.let {
            observeProfileUserUseCase.execute(uid)
        }
    }

    /**
     * 시작하기
     **/
    fun gettingStarted() {
        //프로필 변경 날짜 업데이트¬
        observeProfileModifiedDateUseCase(uid!!)
        startObservable.postValue(Event(true))
    }
}