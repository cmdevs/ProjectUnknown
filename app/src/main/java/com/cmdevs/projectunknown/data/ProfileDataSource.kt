package com.cmdevs.projectunknown.data

import android.util.Log

interface ProfileDataSource {
    fun registerFireStoreProfile(userAuthInfo: UserAuthInfo): ProfileData
}

class FireStoreProfileDataSource : ProfileDataSource {
    override fun registerFireStoreProfile(userAuthInfo: UserAuthInfo): ProfileData {
        Log.d("cylee", "registerFireStoreProfile : ${userAuthInfo}")
        /**
         * todo register when firestore
         **/
        return ProfileData(
            FirebaseUserAuthInfo(
                true,
                "uid",
                "email",
                "displayName",
                "photoUrl"
            )
        )
    }
}