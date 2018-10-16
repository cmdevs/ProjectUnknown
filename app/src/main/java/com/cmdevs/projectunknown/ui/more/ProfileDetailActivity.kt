package com.cmdevs.projectunknown.ui.more

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.util.fragAdd
import com.cmdevs.projectunknown.util.obtainViewModel
import com.cmdevs.projectunknown.viewmodels.ProfileViewModel

class ProfileDetailActivity : AppCompatActivity() {

    lateinit var fragment: ProfileDetailFragment
    lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiledetail)
        viewModel = obtainViewModel()
        setUpFragment()
    }

    fun setUpFragment() {
        fragment = ProfileDetailFragment.newInstance().apply {
            arguments = Bundle().apply {
                putSerializable(
                    "profile",
                    intent.getSerializableExtra("profile")
                )
            }
        }
        fragAdd(R.id.container, fragment)
    }

    fun obtainViewModel(): ProfileViewModel = obtainViewModel(ProfileViewModel::class.java)

}