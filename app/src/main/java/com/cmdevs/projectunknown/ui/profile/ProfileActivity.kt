package com.cmdevs.projectunknown.ui.profile

import android.os.Bundle
import android.util.Log
import com.cmdevs.projectunknown.BaseActivity
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.data.UserAuthInfo
import com.cmdevs.projectunknown.util.inTransaction

class ProfileActivity : BaseActivity() {

    companion object {
        private const val USER_AUTH_INFO_KEY = "UserAuthInfoKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("cylee", "getProfileData : ${intent.getSerializableExtra(USER_AUTH_INFO_KEY)}")
        supportFragmentManager.inTransaction {
            add(R.id.container, ProfileFragment.newInstance().apply {
                arguments = Bundle(
                ).apply {
                    putSerializable(
                        USER_AUTH_INFO_KEY,
                        intent?.getSerializableExtra(USER_AUTH_INFO_KEY)
                    )
                }
            })
        }
    }
}
