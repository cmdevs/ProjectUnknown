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

package com.cmdevs.projectunknown.ui.signin

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.data.signin.AuthenticatedUserInfo
import com.cmdevs.projectunknown.domain.auth.ObserveUserAuthStateUseCase
import com.cmdevs.projectunknown.result.Event
import com.cmdevs.projectunknown.result.Result
import com.cmdevs.projectunknown.util.map

enum class SignInEvent {
    RequestSignIn, RequestSignOut
}

/**
 * Interface to implement sign-in functionality in a ViewModel.
 *
 * You can inject a implementation of this via Dagger2, then use the implementation as an interface
 * delegate to add sign in functionality without writing any code
 *
 * Example usage
 *
 * ```
 * class MyViewModel @Inject constructor(
 *     signInViewModelComponent: SignInViewModelDelegate
 * ) : ViewModel(), SignInViewModelDelegate by signInViewModelComponent {
 * ```
 */
interface SignInDelegate {
    /**
     * Live updated value of the current firebase user
     */
    val currentFirebaseUser: LiveData<Result<AuthenticatedUserInfo>?>

    /**
     * Live updated value of the current firebase users image url
     */
    val currentUserImageUri: LiveData<Uri?>

    /**
     * Emits Events when a sign-in event should be attempted
     */
    val performSignInEvent: MutableLiveData<Event<SignInEvent>>

    /**
     * Emits an non-null Event when the dialog to ask the user notifications preference should be
     * shown.
     */
    val shouldShowNotificationsPrefAction: LiveData<Event<Boolean>>

    /**
     * Emit an Event on performSignInEvent to request sign-in
     */
    fun emitSignInRequest()

    /**
     * Emit an Event on performSignInEvent to request sign-out
     */
    fun emitSignOutRequest()

    fun observeSignedInUser(): LiveData<Boolean>

    fun observeRegisteredUser(): LiveData<Boolean>

    fun isSignedIn(): Boolean

    fun isRegistered(): Boolean

    /**
     * Returns the current user ID or null if not available.
     */
    fun getUserId(): String?
}

/**
 * Implementation of SignInViewModelDelegate that uses Firebase's auth mechanisms.
 */
internal class FirebaseSignInlDelegate(
    observeUserAuthStateUseCase: ObserveUserAuthStateUseCase
) : SignInDelegate {

    override val performSignInEvent = MutableLiveData<Event<SignInEvent>>()
    override val currentFirebaseUser: LiveData<Result<AuthenticatedUserInfo>?>
    override val currentUserImageUri: LiveData<Uri?>
    override val shouldShowNotificationsPrefAction = MediatorLiveData<Event<Boolean>>()

    private val _isRegistered: LiveData<Boolean>
    private val _isSignedIn: LiveData<Boolean>

    init {
        currentFirebaseUser = observeUserAuthStateUseCase.observe()

        currentUserImageUri = currentFirebaseUser.map { result: Result<AuthenticatedUserInfo?>? ->
            (result as? Result.Success)?.data?.getPhotoUrl()
        }

        _isSignedIn = currentFirebaseUser.map {
            isSignedIn()
        }
        _isRegistered = currentFirebaseUser.map { isRegistered() }

        observeUserAuthStateUseCase.execute(Any())
    }

    override fun emitSignInRequest() {
        performSignInEvent.postValue(Event(SignInEvent.RequestSignIn))
    }

    override fun emitSignOutRequest() {
        performSignInEvent.postValue(Event(SignInEvent.RequestSignOut))
    }

    override fun isSignedIn(): Boolean {
        return (currentFirebaseUser.value as? Result.Success)?.data?.isSignedIn() == true
    }

    override fun isRegistered(): Boolean {
        return (currentFirebaseUser.value as? Result.Success)?.data?.isRegistered() == true
    }

    override fun observeSignedInUser(): LiveData<Boolean> {
        return _isSignedIn
    }

    override fun observeRegisteredUser(): LiveData<Boolean> {
        return _isRegistered
    }

    override fun getUserId(): String? {
        val user = currentFirebaseUser.value
        return (user as? Result.Success)?.data?.getUid()
    }
}
