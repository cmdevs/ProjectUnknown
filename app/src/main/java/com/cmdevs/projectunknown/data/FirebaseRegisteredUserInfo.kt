package com.cmdevs.projectunknown.data

import android.net.Uri
import com.google.firebase.auth.FirebaseUser

class FirebaseRegisteredUserInfo(
    val firebaseUserInfoBasic: FirebaseUserInfoBasic?,
    val isRegistered: Boolean?
) : FirebaseUserInfo {

    override fun isRegistered(): Boolean = isRegistered ?: false

    override fun isSignedIn(): Boolean = firebaseUserInfoBasic?.isSignedIn() == true

    override fun getEmail(): String? = firebaseUserInfoBasic?.getEmail()

    //override fun getProviderData(): MutableList<out UserInfo>? = firebaseUserInfoBasic?.getProviderData()

    override fun isAnonymous(): Boolean? = firebaseUserInfoBasic?.isAnonymous()

    override fun getPhoneNumber(): String? = firebaseUserInfoBasic?.getPhoneNumber()

    override fun getUid(): String? = firebaseUserInfoBasic?.getUid()

    override fun isEmailVerified(): Boolean? = firebaseUserInfoBasic?.isEmailVerified()

    override fun getDisplayName(): String? = firebaseUserInfoBasic?.getDisplayName()

    override fun getPhotoUrl(): Uri? = firebaseUserInfoBasic?.getPhotoUrl()

    override fun getProviderId(): String? = firebaseUserInfoBasic?.getProviderId()

    override fun getLastSignInTimestamp(): Long? = firebaseUserInfoBasic?.getLastSignInTimestamp()

    override fun getCreationTimestamp(): Long? = firebaseUserInfoBasic?.getCreationTimestamp()

    override fun isRegistrationDataReady(): Boolean = isRegistered != null
}

class FirebaseUserInfoBasicImpl(
    val firebaseUser: FirebaseUser?
) : FirebaseUserInfoBasic {
    override fun isSignedIn() = firebaseUser != null

    override fun getEmail() = firebaseUser?.email

    override fun getLastSignInTimestamp() = firebaseUser?.metadata?.lastSignInTimestamp

    override fun getCreationTimestamp() = firebaseUser?.metadata?.creationTimestamp

    override fun isAnonymous() = firebaseUser?.isAnonymous

    override fun getPhoneNumber() = firebaseUser?.phoneNumber

    override fun getUid() = firebaseUser?.uid

    override fun isEmailVerified() = firebaseUser?.isEmailVerified

    override fun getDisplayName() = firebaseUser?.displayName

    override fun getPhotoUrl() = firebaseUser?.photoUrl

    override fun getProviderId() = firebaseUser?.providerId
}
