/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cmdevs.projectunknown.data.signin.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.domain.internal.DefaultScheduler
import com.cmdevs.projectunknown.result.Result

/**
 * A [RegisteredUserDataSource] that listens to changes in firestore to indicate whether the
 * current user is registered in the event or not as an attendee.
 */
class FirestoreRegisteredUserDataSource
    : RegisteredUserDataSource {

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val REGISTERED_KEY = "registered"
    }

    //private var registeredChangedListenerSubscription: ListenerRegistration? = null

    // Result can contain a null value (not processed) or a null result (not available).
    private val result = MutableLiveData<Result<Boolean?>?>()

    // Keeping the last observed user ID, to avoid unnecessary calls
    private var lastUserId: String? = null

    /**
     * Listens to changes in the user document in Firestore. A Change in the "registered" field
     * will emit a new user.
     */
    override fun listenToUserChanges(newUserId: String) {
        Log.d("cylee","listenToUserChanges newUserId : ${newUserId}")

        val userId = if (lastUserId != newUserId) {
            newUserId
        } else {
            // No need to refresh
            return
        }
        result.postValue(Result.Success(true))

        lastUserId = userId
        // Remove the previous subscription, if it exists:
        //registeredChangedListenerSubscription?.remove()
        /*result.postValue(null) // Reset result
        // Watch the document:
        val registeredChangedListener =
            { snapshot: DocumentSnapshot?, _: FirebaseFirestoreException? ->
                DefaultScheduler.execute {
                    if (snapshot == null || !snapshot.exists()) {
                        // When the account signs in for the first time, the document doesn't exist
                        result.postValue(Result.Success(false))
                        return@execute
                    }
                    val isRegistered: Boolean? = snapshot.get(REGISTERED_KEY) as? Boolean
                    // Only emit a value if it's a new value or a value change.
                    if (result.value == null ||
                        (result.value as? Result.Success)?.data != isRegistered
                    ) {
                        result.postValue(Result.Success(isRegistered))
                    }
                }
            }
        registeredChangedListenerSubscription = firestore.collection(USERS_COLLECTION)
            .document(userId).addSnapshotListener(registeredChangedListener)
        lastUserId = userId*/
    }

    override fun observeResult(): LiveData<Result<Boolean?>?> {
        return result
    }

    override fun setAnonymousValue() {
        //registeredChangedListenerSubscription?.remove()
        lastUserId = null
        result.postValue(Result.Success(false))
    }
}
