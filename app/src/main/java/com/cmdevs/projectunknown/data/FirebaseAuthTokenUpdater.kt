package com.cmdevs.projectunknown.data

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.iid.FirebaseInstanceId

class FirebaseAuthTokenUpdater(
    val firestore: FirebaseFirestore
) : AuthTokenUpdater {

    override fun updaterToken(token: String) {
        val tk = FirebaseInstanceId.getInstance().token

        if (tk == null) {
            Log.d("cylee", "Error getting FCM ID token for user $token")
            return
        }

        val userInfo = mapOf(
            LAST_VISIT_KEY to FieldValue.serverTimestamp(),
            TOKEN_ID_KEY to tk
        )

        firestore
            .collection(USERS_COLLECTION)
            .document(token)
            .collection(FCM_IDS_COLLECTION)
            .document(token.take(TOKEN_ID_LENGTH))
            .set(userInfo, SetOptions.merge()).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("cylee","ID token successfully uploaded for user $token")
                } else {
                    Log.d("cylee","ID token Error uploaded for user $token")
                }
            }

        val lastUsage = mapOf(
            USER_LAST_USAGE_KEY to FieldValue.serverTimestamp()
        )

        firestore
            .collection(USERS_COLLECTION)
            .document(token)
            .set(lastUsage, SetOptions.merge()).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("cylee","ID token successfully uploaded for user $token")
                } else {
                    Log.d("cylee","ID token Error uploaded for user $token")
                }
            }
    }

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val LAST_VISIT_KEY = "lastVisit"
        private const val USER_LAST_USAGE_KEY = "lastUsage"
        private const val TOKEN_ID_KEY = "tokenId"
        private const val FCM_IDS_COLLECTION = "fcmTokens"
        private const val TOKEN_ID_LENGTH = 25
    }
}