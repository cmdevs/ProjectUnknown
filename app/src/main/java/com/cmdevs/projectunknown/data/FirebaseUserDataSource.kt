package com.cmdevs.projectunknown.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.domain.result.Result
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import timber.log.Timber

class FirebaseUserDataSource(
        val firestore: FirebaseFirestore
) : UserDataSource {

    private val userObservable = MutableLiveData<com.cmdevs.projectunknown.domain.result.Result<ProfileData>>()

    override fun getUser(uid: String) {
        firestore.collection(USERS_COLLECTION)
                .document(uid)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.getResult()?.let { snapshot ->
                            snapshot.run {
                                val email = get(USER_EMAIL) as String
                                val id = get(USER_ID) as String
                                val name = get(USER_NAME) as String
                                val photoUrl = get(USER_PHOTO_URL) as String
                                userObservable.postValue(com.cmdevs.projectunknown.domain.result.Result.Success(ProfileData(id, name, photoUrl, email)))
                            }
                        } ?: let {
                            Timber.d("profile snapshot not exist")
                        }
                    } else {
                        Timber.d("task failure")
                    }
                }
    }

    /**
     * 프로파일 수정 날짜
     **/
    val updateDate = mapOf(
            USER_PROFILE_UPDATE_DATE to FieldValue.serverTimestamp()
    )

    override fun modifiedDate(uid: String) =
            firestore.collection(USERS_COLLECTION)
                    .document(uid)
                    .set(updateDate, SetOptions.merge()).isSuccessful


    override fun observeUser(): LiveData<Result<ProfileData>> = userObservable

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val USER_EMAIL = "email"
        private const val USER_ID = "id"
        private const val USER_NAME = "name"
        private const val USER_PHOTO_URL = "photoUrl"
        private const val USER_PROFILE_UPDATE_DATE = "profileUpdateDate"
    }
}