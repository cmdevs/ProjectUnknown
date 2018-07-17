package com.cmdevs.projectunknown.view.friend

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.data.friend.FriendListRepository
import com.cmdevs.projectunknown.view.friend.adapter.FriendListAdapter
import com.cmdevs.projectunknown.view.friend.viewmodel.FriendFactory
import com.cmdevs.projectunknown.view.friend.viewmodel.FriendViewModel

class FriendFragment : Fragment() {

    val adapter: FriendListAdapter by lazy {
        FriendListAdapter(this@FriendFragment.context)
    }

    val viewModel: FriendViewModel by lazy {
        ViewModelProviders.of(
            this, FriendFactory(FriendListRepository, adapter, lifecycle)
        ).get(FriendViewModel::class.java)
    }

    companion object {
        fun newInstance() = FriendFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = LayoutInflater.from(context).inflate(R.layout.fragment_friend, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("cylee", "onActivity()")

        viewModel.friendDatas.observe(this, Observer {
            Log.d("cylee", "it - $it")
        })

        viewModel.getFriendList()
    }

}

