package com.cmdevs.projectunknown.data

import android.net.Uri
import java.io.Serializable

interface UserAuthInfo {

    fun isSignIn(): Boolean
    fun getEmail(): String?
    fun getDisplayName(): String?
    fun getPhotoUrl(): Uri?
}

open class FirebaseUserAuthInfo(
    val userSigning: Boolean,
    val userEmail: String?,
    val userDisplayName: String?,
    val userPhotoUrl: Uri?
) : UserAuthInfo, Serializable {

    override fun isSignIn(): Boolean {
        return userSigning
    }

    override fun getEmail(): String? {
        return userEmail
    }

    override fun getDisplayName(): String? {
        return userDisplayName
    }

    override fun getPhotoUrl(): Uri? {
        return userPhotoUrl
    }
}