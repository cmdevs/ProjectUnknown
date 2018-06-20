package com.cmdevs.projectunknown.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.util.replace
import com.cmdevs.projectunknown.view.friend.FriendFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.apply {
            setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navi_friend -> {
                        replace(R.id.mainContainer, FriendFragment.newInstance())
                        true
                    }

                    R.id.navi_map -> {
                        true
                    }

                    R.id.navi_timeline -> {
                        true
                    }

                    R.id.navi_more -> {
                        true
                    }
                }
                false
            })
        }

        replace(R.id.mainContainer, FriendFragment.newInstance())
    }

}
