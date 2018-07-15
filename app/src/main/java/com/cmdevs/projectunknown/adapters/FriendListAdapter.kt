package com.cmdevs.projectunknown.adapters

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cmdevs.projectunknown.adapters.holders.FriendViewHolder
import com.cmdevs.projectunknown.data.Friend
import com.cmdevs.projectunknown.databinding.FriendItemBinding

class FriendListAdapter
    : PagedListAdapter<Friend, FriendViewHolder>(diffCallback) {

    lateinit var binding: FriendItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        binding = FriendItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.onCreateViewHolder(item)
        }
    }

    /**
     * TODO
     *  1. diff 정의 파악 후 내 상황에 만든 로직 구현
     **/
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Friend>() {
            override fun areItemsTheSame(
                oldItem: Friend?,
                newItem: Friend?
            ): Boolean = oldItem?.userId == newItem?.userId

            override fun areContentsTheSame(
                oldItem: Friend?,
                newItem: Friend?
            ): Boolean = oldItem == newItem
        }
    }


}