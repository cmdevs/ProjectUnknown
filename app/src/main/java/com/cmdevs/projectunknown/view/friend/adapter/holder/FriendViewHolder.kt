package com.cmdevs.projectunknown.view.friend.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.cmdevs.projectunknown.data.FriendListData
import com.cmdevs.projectunknown.view.friend.FriendItemViewModel

class FriendViewHolder(
    view: View,
    private val friendItemViewModel: FriendItemViewModel
) : RecyclerView.ViewHolder(view) {

    fun onBind(item: FriendListData) {
        friendItemViewModel.setFriendData(item)
    }

}