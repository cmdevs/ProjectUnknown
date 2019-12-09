package com.cmdevs.projectunknown.data

import com.google.firebase.auth.FirebaseUser

interface AuthTokenUpdater {
    fun updaterToken(firebaseUser: FirebaseUser)
}