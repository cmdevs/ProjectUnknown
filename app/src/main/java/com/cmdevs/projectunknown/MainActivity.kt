package com.cmdevs.projectunknown

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.cmdevs.projectunknown.util.fragAdd
import com.cmdevs.projectunknown.util.fragReplace
import com.cmdevs.projectunknown.util.obtainViewModel
import com.cmdevs.projectunknown.view.friend.FriendFragment
import com.cmdevs.projectunknown.view.friend.FriendNavigator
import com.cmdevs.projectunknown.viewmodel.FriendViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FriendNavigator {

    lateinit var viewModel: FriendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBottomNavigation()
        setFragment()

        viewModel = obtainViewModel().apply {
            //doSomething...
        }

    }

    fun setFragment() {
        fragAdd(R.id.mainContainer, FriendFragment.newInstance())
    }

    fun setBottomNavigation() {
        navigation.apply {
            setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navi_friend -> {
                        fragReplace(R.id.mainContainer, FriendFragment.newInstance())
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
    }

    fun obtainViewModel(): FriendViewModel = obtainViewModel(FriendViewModel::class.java)

}
