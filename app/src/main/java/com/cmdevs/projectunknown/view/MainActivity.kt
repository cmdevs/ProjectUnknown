package com.cmdevs.projectunknown.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.cmdevs.projectunknown.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.apply {
            setOnNavigationItemSelectedListener( BottomNavigationView.OnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.navi_friend -> {

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

}
