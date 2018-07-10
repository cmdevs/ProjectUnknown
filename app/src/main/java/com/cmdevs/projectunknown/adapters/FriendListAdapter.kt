package com.cmdevs.projectunknown.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cmdevs.projectunknown.adapters.holders.FriendViewHolder
import com.cmdevs.projectunknown.databinding.FriendItemBinding

class FriendListAdapter(
    context: Context?
) : BaseAdapter(context) {

    lateinit var binding: FriendItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = FriendItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FriendViewHolder(binding)
    }

}