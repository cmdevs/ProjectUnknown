package com.cmdevs.projectunknown.adapters.holders

import android.util.Log
import android.view.View
import com.cmdevs.projectunknown.data.FriendListData
import com.cmdevs.projectunknown.databinding.FriendItemBinding

class FriendViewHolder(
    private val binding: FriendItemBinding
) : BaseViewHolder<FriendListData>(binding.root) {

    override val containerView: View?
        get() = binding.root

    override fun onCreateViewHolder(item: FriendListData) {
        binding.friend = item
        binding.executePendingBindings()
    }

}