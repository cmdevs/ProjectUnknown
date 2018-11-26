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
import com.cmdevs.projectunknown.data.signin.AuthenticatedUserInfoBasic
import com.cmdevs.projectunknown.data.signin.FirebaseUserInfo
import com.cmdevs.projectunknown.domain.internal.DefaultScheduler
import com.cmdevs.projectunknown.result.Result
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult

/**
 * An [AuthStateUserDataSource] that listens to changes in [FirebaseAuth].
 *
 * When a [FirebaseUser] is available, it
 *  * Posts it to the user observable
 *  * Fetches the ID token
 *  * Uses the ID token to trigger the registration point
 *  * Stores the FCM ID Token in Firestore
 *  * Posts the user ID to the observable
 *
 * This data source doesn't find if a user is registered or not (is an attendee). Once the
 * registration point is called, the server will generate a field in the user document, which
 * is observed by [RegisteredUserDataSource] in its implementation
 * [FirestoreRegisteredUserDataSource].
 */
class FirebaseAuthStateUserDataSource(
    val firebaseAuth: FirebaseAuth
) : AuthStateUserDataSource {

    private val currentFirebaseUserObservable =
        MutableLiveData<Result<AuthenticatedUserInfoBasic?>>()


    private var isAlreadyListening = false

    // Listener that saves the [FirebaseUser], fetches the ID token, calls the registration point
    // and updates the user ID observable.
    private val authStateListener: ((FirebaseAuth) -> Unit) = { auth ->
        DefaultScheduler.execute {
            Log.d("cylee","authStateListener")
            currentFirebaseUserObservable.postValue(
                Result.Success (
                    FirebaseUserInfo(auth.currentUser)
                )
            )
            auth.currentUser?.let { currentUser ->
                // Get the ID token (force refresh)
                val tokenTask = currentUser.getIdToken(true)
                try {
                    // Do this synchronously
                    val await: GetTokenResult = Tasks.await(tokenTask)
                    await.token?.let {
                        // Call registration point to generate a result in Firestore
                        /**
                         * 통신구간??
                         **/
                        //AuthenticatedUserRegistration.callRegistrationEndpoint(it)
                    }
                } catch (e: Exception) {
                    return@let
                }
            }
        }
    }

    override fun startListening() {
        if (!isAlreadyListening) {
            firebaseAuth.addAuthStateListener(authStateListener)
            isAlreadyListening = true
        }
    }

    override fun getBasicUserInfo(): LiveData<Result<AuthenticatedUserInfoBasic?>> {
        return currentFirebaseUserObservable
    }

    override fun clearListener() {
        firebaseAuth.removeAuthStateListener(authStateListener)
    }
}
