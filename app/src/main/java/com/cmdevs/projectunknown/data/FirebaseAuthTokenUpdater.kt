package com.cmdevs.projectunknown.data

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import timber.log.Timber

class FirebaseAuthTokenUpdater(
    val firestore: FirebaseFirestore
) : AuthTokenUpdater {

    override fun updaterToken(firebaseUser: FirebaseUser) {
        val token = firebaseUser.uid
        Timber.d("token $token")

        if (token == null) {
            Timber.d("Error getting FCM ID token for user $token")
            return
        }

        val userInfo = mapOf(
            USER_ID_KEY to firebaseUser.uid,
            USER_EMAIL_KEY to firebaseUser.email,
            USER_NAME_KEY to firebaseUser.displayName,
            USER_PHOTO_URL_KEY to firebaseUser.photoUrl?.toString(),
            LAST_VISIT_KEY to FieldValue.serverTimestamp()
        )

        firestore
            .collection(USERS_COLLECTION)
            .document(token)
            .set(userInfo, SetOptions.merge()).addOnCompleteListener {
                if (it.isSuccessful) {
                    Timber.d("ID token successfully uploaded for user $token")
                } else {
                    Timber.d("ID token Error uploaded for user $token")
                }
            }
    }

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val USER_ID_KEY = "id"
        private const val USER_EMAIL_KEY = "email"
        private const val USER_NAME_KEY = "name"
        private const val USER_PHOTO_URL_KEY = "photoUrl"
        private const val LAST_VISIT_KEY = "lastVisit"
        private const val REGISTERED_KEY = "registered"
    }
}