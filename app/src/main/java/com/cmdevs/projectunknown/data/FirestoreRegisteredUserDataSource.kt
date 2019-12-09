package com.cmdevs.projectunknown.data

import androidx.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.domain.internal.DefaultScheduler
import com.cmdevs.projectunknown.domain.result.Result
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import timber.log.Timber

class FirestoreRegisteredUserDataSource(
    val firestore: FirebaseFirestore
) : RegisteredUserDataSource {

    private var registeredChangedListenerSubscription: ListenerRegistration? = null
    private val result = MutableLiveData<Result<Boolean?>?>()
    private var lastUserId: String? = null

    override fun listenToUserChanges(userId: String) {
        val userId = if (lastUserId != userId) {
            userId
        } else {
            // noRefresh
            Timber.d("userId == lastUserId")
            return
        }

        Timber.d("userId $userId")

        //clean listener
        registeredChangedListenerSubscription?.remove()
        result.postValue(null)

        val registeredChangedListener =
            { snapshot: DocumentSnapshot?, error: FirebaseFirestoreException? ->
                Timber.d("registeredChangedListener trigger")
                DefaultScheduler.execute {
                    if (error != null) {
                        Timber.d("registeredChangedListener error ${error.message}")
                        return@execute
                    }

                    Timber.d("snapshot exist ${snapshot?.exists()}")
                    if (snapshot == null || !snapshot.exists()) {
                        Timber.d("snapshot first if")
                        // When the account signs in for the first time, the document doesn't exist
                        result.postValue(Result.Success(false))
                        return@execute
                    }

                    val isRegisterdId = (snapshot.get(REGISTERED_KEY) as? Boolean)?.let {
                        Timber.d("it $it")
                        it
                    } ?: let {
                        Timber.d("it false")
                        false
                    }
                    Timber.d("isRegisterdId ${isRegisterdId}")

                    if(result.value == null || (result.value as? Result.Success)?.data != isRegisterdId) {
                        result.postValue(Result.Success(isRegisterdId))
                    }
                }
            }

        registeredChangedListenerSubscription = firestore.collection(USERS_COLLECTION)
            .document(userId).addSnapshotListener(registeredChangedListener)
        lastUserId = userId
        Timber.d("lastUserId $lastUserId")
    }

    override fun observeResult() = result

    override fun setAnonymousValue() {

    }

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val USER_ID_KEY = "id"
        private const val REGISTERED_KEY = "registered"
    }
}