package com.cmdevs.projectunknown.data

import android.net.Uri
import com.google.firebase.auth.FirebaseUser

interface UserAuthInfo {

    fun isSignIn(): Boolean
    fun getEmail(): String?
    fun getDisplayName(): String?
    fun getPhotoUrl(): Uri?
}

open class FacebookUserAuthInfo(
    private val firebaseUser: FirebaseUser?
) : UserAuthInfo {

    override fun isSignIn(): Boolean {
        return firebaseUser != null
    }

    override fun getEmail(): String? {
        return firebaseUser?.email
    }

    override fun getDisplayName(): String? {
        return firebaseUser?.displayName
    }

    override fun getPhotoUrl(): Uri? {
        return firebaseUser?.photoUrl
    }
}


