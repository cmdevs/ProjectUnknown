package com.cmdevs.projectunknown.view.friend

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdevs.projectunknown.MainActivity
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.adapters.FriendListAdapter
import com.cmdevs.projectunknown.data.FriendListData
import com.cmdevs.projectunknown.viewmodel.FriendViewModel
import kotlinx.android.synthetic.main.fragment_friend.*

class FriendFragment : Fragment() {

    lateinit var viewModel: FriendViewModel
    lateinit var adapter: FriendListAdapter

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
        viewModel = (activity as MainActivity).obtainViewModel()

        setRecyclerView()

        subscribeUi()
    }

    fun setRecyclerView() {
        adapter = FriendListAdapter(
            this@FriendFragment.context
        )

        recyclerView.run {
            adapter = this@FriendFragment.adapter
        }
    }

    fun subscribeUi() {
        viewModel.run {
            getFriends().observe(this@FriendFragment, Observer {
                it?.let {
                    adapter.setItem(it)
                }
            })
        }
    }


}

