package com.cmdevs.projectunknown.domain.auth

import com.cmdevs.projectunknown.data.*
import com.cmdevs.projectunknown.domain.MediatorUseCase
import com.cmdevs.projectunknown.domain.result.Result

class ObserveUserAuthStateUseCase(
    val firebaseAuthStateUserDataSource: AuthStateUserDataSource,
    val firestoreRegisteredUserDataSource: RegisteredUserDataSource
) : MediatorUseCase<Any, FirebaseUserInfo>() {

    val firebaseUserObservable = firebaseAuthStateUserDataSource.getObservableUserInfo()
    val isUserRegisteredObserve = firestoreRegisteredUserDataSource.observeResult()

    init {
        result.addSource(firebaseUserObservable) {
            val firebaseUserInfoBasic = (it as? Result.Success)?.data
            firebaseUserInfoBasic?.let { firebaseUserInfo ->
                //firestore 접근
                firebaseUserInfo.getUid()?.let {
                    firestoreRegisteredUserDataSource.listenToUserChanges(it)
                }

                //signOut 처리
                //error 처리
            } ?: let {
                //Result.Error 처리
            }
        }

        result.addSource(isUserRegisteredObserve) {
            val firebaseUserInfoBasic = (firebaseUserObservable.value as? Result.Success)?.data
            val isRegistered = (isUserRegisteredObserve.value as? Result.Success)?.data
            firebaseUserInfoBasic?.let { firebaseUserInfoBasic ->
                isRegistered?.let {
                    updateUserObserve(firebaseUserInfoBasic, it)
                }
            } ?: let {
                //error 처리
            }
        }
    }

    fun updateUserObserve(firebaseUserInfo: FirebaseUserInfoBasic, isRegistered: Boolean) {
        result.postValue(
            Result.Success(
                FirebaseRegisteredUserInfo(firebaseUserInfo, isRegistered)
            )
        )
    }

    override fun execute(parameters: Any) {
        firebaseAuthStateUserDataSource.startingListener()
    }
}