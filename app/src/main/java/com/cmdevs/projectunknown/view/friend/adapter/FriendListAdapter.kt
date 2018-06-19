package com.cmdevs.projectunknown.view.friend.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.data.FriendListData
import com.cmdevs.projectunknown.databinding.FriendItemBinding
import com.cmdevs.projectunknown.view.friend.FriendItemViewModel
import com.cmdevs.projectunknown.view.friend.adapter.holder.FriendViewHolder
import com.cmdevs.projectunknown.view.friend.adapter.model.FriendRecyclerModel

class FriendListAdapter(private val friendItemViewModel: FriendItemViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), FriendRecyclerModel {

    val itemList = mutableListOf<FriendListData>()
    lateinit var friendItemBinding: FriendItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent, false)
        friendItemBinding = FriendItemBinding.bind(view)
        friendItemBinding.viewmodel = friendItemViewModel

        return FriendViewHolder(friendItemBinding.root, friendItemViewModel)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FriendViewHolder)?.onBind(itemList[position])
    }

    override fun addItem(list: FriendListData) {
        itemList.add(list)
    }

    override fun getItem(position: Int) = itemList[position]

    override fun getItemCount() = itemList.size

    override fun notifyDataSetChange() {
        notifyDataSetChange()
    }

}

