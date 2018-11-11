package com.cmdevs.projectunknown.ui

import android.os.Bundle
import android.util.Log
import com.cmdevs.projectunknown.BaseActivity
import com.cmdevs.projectunknown.R

class ProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        Log.d("cylee","getProfileData : ${intent.getSerializableExtra("user")}")
    }
}
