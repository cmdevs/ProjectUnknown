package com.cmdevs.projectunknown.data

import androidx.lifecycle.LiveData
import com.cmdevs.projectunknown.result.Result
import com.google.firebase.auth.FirebaseAuth

interface SignInStateDataSource {

    val authListener: (FirebaseAuth) -> Unit
    fun requestSignIn(emailUserInfo: EmailUserInfo)
    fun triggerListener()
    fun clearListener()
    fun observe(): LiveData<Result<UserAuthInfo>>
}