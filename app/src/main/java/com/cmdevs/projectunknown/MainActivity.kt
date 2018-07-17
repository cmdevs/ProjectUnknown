package com.cmdevs.projectunknown

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.cmdevs.projectunknown.util.Injection
import com.cmdevs.projectunknown.util.fragAdd
import com.cmdevs.projectunknown.util.fragReplace
import com.cmdevs.projectunknown.util.obtainViewModel
import com.cmdevs.projectunknown.view.friend.FriendFragment
import com.cmdevs.projectunknown.view.friend.FriendNavigator
import com.cmdevs.projectunknown.viewmodels.FriendViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FriendNavigator {

    lateinit var viewModel: FriendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBottomNavigation()
        setFragment()

        //val factory = Injection.provideFriendListViewModelFactory(this)
        //viewModel = ViewModelProviders.of(this, factory).get(FriendViewModel::class.java)
        viewModel = obtainViewModel()

        subscribeUi()
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

    fun subscribeUi() {
        viewModel.run {
            getNewFriendEvent().observe(this@MainActivity, Observer {
                //doSomething..when floating clicked
                Log.d("cylee","doSomething..when floating clicked")
            })
        }
    }

}
