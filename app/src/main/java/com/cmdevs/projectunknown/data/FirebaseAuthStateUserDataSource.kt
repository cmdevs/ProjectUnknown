package com.cmdevs.projectunknown.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.domain.internal.DefaultScheduler
import com.cmdevs.projectunknown.domain.result.Result
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber

class FirebaseAuthStateUserDataSource(
    val firebaseAuth: FirebaseAuth,
    val authTokenUpdater: AuthTokenUpdater
) : AuthStateUserDataSource {

    val firebaseUserInfoBasicObservable = MutableLiveData<Result<FirebaseUserInfoBasic?>>()
    var isAlreadyAuthListening = false



    val firebaseAuthStateListener: (FirebaseAuth) -> Unit = { firebaseAuth ->
        DefaultScheduler.execute {
            Timber.d("firebase currentUser ${firebaseAuth.currentUser}")

            firebaseUserInfoBasicObservable.postValue(
                Result.Success(FirebaseUserInfoBasicImpl(firebaseAuth.currentUser))
            )

            firebaseAuth.currentUser?.let { firebaseUser ->
                authTokenUpdater.updaterToken(firebaseUser)
            }
        }
    }

    override fun startingListener() {
        if (!isAlreadyAuthListening) {
            firebaseAuth.addAuthStateListener(firebaseAuthStateListener)
            isAlreadyAuthListening = true
        }
    }

    override fun clearListener() {
        firebaseAuth.removeAuthStateListener(firebaseAuthStateListener)
    }

    override fun getObservableUserInfo() = firebaseUserInfoBasicObservable
}