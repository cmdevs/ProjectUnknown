package com.cmdevs.projectunknown.view.friend

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdevs.projectunknown.MainActivity
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.adapters.FriendListAdapter
import com.cmdevs.projectunknown.util.Injection
import com.cmdevs.projectunknown.viewmodels.FriendViewModel
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

        //val factory = Injection.provideFriendListViewModelFactory(activity as MainActivity)
        //viewModel = ViewModelProviders.of((activity as MainActivity), factory).get(FriendViewModel::class.java)
        viewModel = (activity as MainActivity).obtainViewModel()

        setupRecyclerView()
        setupFabButtion()
        subscribeUi()
    }

    fun setupRecyclerView() {
        adapter = FriendListAdapter()

        recyclerView.run {
            adapter = this@FriendFragment.adapter
        }
    }

    fun setupFabButtion() {
        activity?.findViewById<FloatingActionButton>(R.id.fabNewFriend)?.apply {
            setOnClickListener {
                viewModel.addNewFriend()
            }
        }
    }

    fun subscribeUi() {
        viewModel.run {
            getFriends().observe(this@FriendFragment, Observer {
                Log.d("cylee", "observe()")
                it?.let {
                    adapter.submitList(it)
                }
            })
        }
    }


}

