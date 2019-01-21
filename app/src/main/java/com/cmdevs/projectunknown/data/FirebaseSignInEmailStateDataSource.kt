package com.cmdevs.projectunknown.data

import androidx.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.result.Result
import com.google.firebase.auth.FirebaseAuth

class FirebaseSignInEmailStateDataSource(
    val firebaseAuth: FirebaseAuth
) : SignInStateDataSource {

    val currentFirebaseEmailUserObservable = MutableLiveData<Result<UserAuthInfo>>()

    override val authListener: (FirebaseAuth) -> Unit = {
        //
    }

    override fun requestSignIn(emailUserInfo: EmailUserInfo) {
        currentFirebaseEmailUserObservable.postValue(Result.Loading)
        when (emailUserInfo) {
            is EmailSignInUserInfo -> {
                firebaseAuth.signInWithEmailAndPassword(
                    emailUserInfo.eamilId,
                    emailUserInfo.emailPassword
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        currentFirebaseEmailUserObservable.postValue(
                            Result.Success(
                                FirebaseUserAuthInfo(
                                    firebaseAuth.currentUser != null,
                                    firebaseAuth.currentUser?.uid,
                                    firebaseAuth.currentUser?.email,
                                    firebaseAuth.currentUser?.displayName,
                                    firebaseAuth.currentUser?.photoUrl.toString()
                                )
                            )
                        )
                    } else {
                        currentFirebaseEmailUserObservable.postValue(
                            Result.Error(
                                it.exception
                            )
                        )
                    }
                }
            }
            is EmailRegisterUserInfo -> {
                firebaseAuth.createUserWithEmailAndPassword(
                    emailUserInfo.emailId,
                    emailUserInfo.emailPassword
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        currentFirebaseEmailUserObservable.postValue(
                            Result.Success(
                                FirebaseUserAuthInfo(
                                    firebaseAuth.currentUser != null,
                                    firebaseAuth.currentUser?.uid,
                                    firebaseAuth.currentUser?.email,
                                    firebaseAuth.currentUser?.displayName,
                                    firebaseAuth.currentUser?.photoUrl.toString()
                                )
                            )
                        )
                    } else {
                        currentFirebaseEmailUserObservable.postValue(
                            Result.Error(
                                it.exception
                            )
                        )
                    }
                }
            }
        }
    }

    override fun triggerListener() {
        //
    }

    override fun clearListener() {
        //
    }

    override fun observe() = currentFirebaseEmailUserObservable
}