package com.cmdevs.projectunknown.data

import android.util.Log
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
            return
        }

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

                    val isRegistered: Boolean? = snapshot.get(REGISTERED_KEY) as? Boolean
                    Timber.d("isRegistered ${isRegistered}")

                    Timber.d("result value ${result.value}")
                    Timber.d("(result.value as? Result.Success)?.data ${(result.value as? Result.Success)?.data}")

                    // Only emit a value if it's a new value or a value change.
                    if (result.value == null ||
                        (result.value as? Result.Success)?.data != isRegistered
                    ) {
                        Log.d("cylee", "Received registered flag: $isRegistered")
                        result.postValue(Result.Success(isRegistered))
                    } else {
                        Timber.d("snapshot second else else else")
                    }
                }
            }

        registeredChangedListenerSubscription = firestore.collection(USERS_COLLECTION)
            .document(userId).addSnapshotListener(registeredChangedListener)
        lastUserId = userId
    }

    override fun observeResult() = result

    override fun setAnonymousValue() {

    }

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val REGISTERED_KEY = "registered"
    }
}