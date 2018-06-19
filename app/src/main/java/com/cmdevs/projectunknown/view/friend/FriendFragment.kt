package com.cmdevs.projectunknown.view.friend

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.data.friend.FriendListRepository
import com.cmdevs.projectunknown.view.friend.adapter.FriendListAdapter
import kotlinx.android.synthetic.main.fragment_friend.*

class FriendFragment : Fragment() {

    private val friendViewModel: FriendViewModel by lazy {
        FriendViewModel(FriendListRepository, friendListAdapter)
    }
    private val friendItemViewModel: FriendItemViewModel by lazy {
        FriendItemViewModel(FriendListRepository)
    }

    private val friendListAdapter: FriendListAdapter by lazy {
        FriendListAdapter(friendItemViewModel)
    }

    companion object {
        fun getInstance() = FriendFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = LayoutInflater.from(context).inflate(R.layout.fragment_friend, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        getFriendInfo()
    }

    fun setRecyclerView() {
        recyclerView.run {
            adapter = friendListAdapter
        }
    }

    fun getFriendInfo() {
        friendViewModel.getFriendInfo()
    }

}

