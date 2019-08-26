package com.cmdevs.projectunknown.data

import androidx.lifecycle.LiveData
import com.cmdevs.projectunknown.domain.result.Result

interface RegisteredUserDataSource {
    fun listenToUserChanges(userId: String)
    fun observeResult(): LiveData<Result<Boolean?>?>
    fun setAnonymousValue()
}