package com.cmdevs.projectunknown.adapters.holders

import android.view.View
import com.cmdevs.projectunknown.data.Friend
import com.cmdevs.projectunknown.databinding.FriendItemBinding

class FriendViewHolder(
    private val binding: FriendItemBinding
) : BaseViewHolder<Friend>(binding.root) {

    override val containerView: View?
        get() = binding.root

    override fun onCreateViewHolder(item: Friend) {
        binding.friend = item
        binding.executePendingBindings()
    }

}