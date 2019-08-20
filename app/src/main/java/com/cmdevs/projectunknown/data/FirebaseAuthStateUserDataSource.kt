package com.cmdevs.projectunknown.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.domain.internal.DefaultScheduler
import com.cmdevs.projectunknown.domain.result.Result
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthStateUserDataSource(
    val firebaseAuth: FirebaseAuth
) : AuthStateUserDataSource {

    val firebaseUserInfoBasicObservable = MutableLiveData<Result<FirebaseUserInfoBasic?>>()
    var isAlreadyAuthListening = false
    val firebaseAuthStateListener: (FirebaseAuth) -> Unit = { firebaseAuth ->
        DefaultScheduler.execute {
            firebaseUserInfoBasicObservable.postValue(
                Result.Success(FirebaseUserInfoBasicImpl(firebaseAuth.currentUser))
            )

            firebaseAuth.currentUser?.let { firebaseUser ->
                val token = firebaseUser.getIdToken(true)
                val result = Tasks.await(token)
                Log.d("cylee", "token : ${token}")
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