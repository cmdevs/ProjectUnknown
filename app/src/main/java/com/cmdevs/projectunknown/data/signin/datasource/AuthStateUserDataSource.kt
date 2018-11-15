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

import androidx.lifecycle.LiveData
import com.cmdevs.projectunknown.data.signin.AuthenticatedUserInfoBasic
import com.cmdevs.projectunknown.result.Result

/**
 * Listens to an Authentication state data source that emits updates on the current user.
 *
 * @see FirebaseAuthStateUserDataSource
 */
interface AuthStateUserDataSource {
    /**
     * Listens to changes in the authentication-related user info.
     */
    fun startListening()

    /**
     * Returns an observable of the [AuthenticatedUserInfoBasic].
     */
    fun getBasicUserInfo(): LiveData<Result<AuthenticatedUserInfoBasic?>>

    /**
     * Call this method to clear listeners to avoid leaks.
     */
    fun clearListener()
}
