package com.cmdevs.projectunknown.data

import android.net.Uri

interface FirebaseUserInfo : FirebaseUserInfoBasic, FirebaseUserInfoRegisterd

interface FirebaseUserInfoBasic {
    fun isSignedIn(): Boolean

    fun getEmail(): String?

    //fun getProviderData(): MutableList<out UserInfo>?

    fun getLastSignInTimestamp(): Long?

    fun getCreationTimestamp(): Long?

    fun isAnonymous(): Boolean?

    fun getPhoneNumber(): String?

    fun getUid(): String?

    fun isEmailVerified(): Boolean?

    fun getDisplayName(): String?

    fun getPhotoUrl(): Uri?

    fun getProviderId(): String?
}

interface FirebaseUserInfoRegisterd {

    fun isRegistered(): Boolean
    fun isRegistrationDataReady(): Boolean
}