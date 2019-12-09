package com.cmdevs.projectunknown.ui

import android.os.Bundle
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.util.inTransaction
import org.kodein.di.Kodein
import org.kodein.di.subKodein

class ProfileActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val uid: String = intent.getStringExtra(ProfileFragment.ARG_UID)
        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                add(R.id.frameLayout, ProfileFragment.newInstance(uid))
            }
        }
    }
}