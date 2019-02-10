package com.cmdevs.projectunknown.data

import java.io.Serializable

interface UserAuthInfo {

    fun isSignIn(): Boolean
    fun getEmail(): String?
    fun getDisplayName(): String?
    fun getPhotoUrl(): String?
    fun getUid(): String?
}

open class FirebaseUserAuthInfo(
    val userSigning: Boolean,
    val userUid: String?,
    val userEmail: String?,
    val userDisplayName: String?,
    val userPhotoUrl: String?
) : UserAuthInfo, Serializable {

    override fun isSignIn(): Boolean {
        return userSigning
    }

    override fun getEmail(): String? {
        return userEmail
    }

    override fun getUid(): String? {
        return userUid
    }

    override fun getDisplayName(): String? {
        return userDisplayName
    }

    override fun getPhotoUrl(): String? {
        return userPhotoUrl
    }
}