package com.cmdevs.projectunknown.data

import androidx.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.result.Result
import com.google.firebase.auth.FirebaseAuth

open class FirebaseSignInStateDataSource(
    val firebaseAuth: FirebaseAuth
) : SignInStateDataSource {

    private val currentFirebaseUserObservable = MutableLiveData<Result<UserAuthInfo>>()

    override val authListener: (FirebaseAuth) -> Unit = { firebaseAuth ->
        currentFirebaseUserObservable.postValue(
            Result.Success(
                FirebaseUserAuthInfo(
                    firebaseAuth.currentUser != null,
                    firebaseAuth.currentUser?.email,
                    firebaseAuth.currentUser?.displayName,
                    firebaseAuth.currentUser?.photoUrl
                )
            )
        )
        //TODO : try coneection HTTPS
    }

    override fun requestSignIn(emailUserInfo: EmailUserInfo) {
        //
    }

    override fun triggerListener() {
        firebaseAuth.addAuthStateListener(authListener)
    }

    override fun clearListener() {
        firebaseAuth.removeAuthStateListener(authListener)
    }

    override fun observe() = currentFirebaseUserObservable
}
