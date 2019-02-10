package com.cmdevs.projectunknown

import android.os.Bundle
import androidx.navigation.Navigation
import com.cmdevs.projectunknown.util.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //주석테스트
        navigation.setupWithNavController(
            Navigation.findNavController(
                this,
                R.id.nav_host_fragment
            )
        )
    }
}
