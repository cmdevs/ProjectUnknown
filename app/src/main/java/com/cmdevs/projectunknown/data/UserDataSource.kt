package com.cmdevs.projectunknown.data

import androidx.lifecycle.LiveData
import com.cmdevs.projectunknown.domain.result.Result

interface UserDataSource {
    fun getUser(uid: String)
    fun modifiedDate(uid: String): Boolean
    fun observeUser(): LiveData<Result<ProfileData>>
}