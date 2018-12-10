package com.cmdevs.projectunknown.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.result.Result
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

open class FacebookAccessTokenStateDataSource(
    private val firebaseAuth: FirebaseAuth,
    private val loginManager: LoginManager,
    private val callbackManager: CallbackManager
) : AccessTokenStateDataSource {

    private val currentFacebookAuthInfo = MutableLiveData<Result<UserAuthInfo>>()

    override fun triggerListener() {
        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                (result?.accessToken)?.let {
                    FacebookAuthProvider.getCredential(it.token).run {
                        firebaseAuth.signInWithCredential(
                            this
                        ).addOnCompleteListener {
                            if (it.isSuccessful) {
                                currentFacebookAuthInfo.postValue(
                                    Result.Success(
                                        FacebookUserAuthInfo(
                                            firebaseAuth.currentUser
                                        )
                                    )
                                )
                            }
                        }
                    }
                }
            }

            override fun onCancel() {
                currentFacebookAuthInfo.postValue(
                    Result.Error(
                        Exception(
                            "onCancel"
                        )
                    )
                )
            }

            override fun onError(error: FacebookException?) {
                currentFacebookAuthInfo.postValue(
                    Result.Error(
                        Exception(
                            error?.message
                        )
                    )
                )
            }
        })
    }

    override fun getAuthInfo(): LiveData<Result<UserAuthInfo>> {
        return currentFacebookAuthInfo
    }
}