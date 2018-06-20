package com.cmdevs.projectunknown.view.friend

import com.cmdevs.projectunknown.data.friend.FriendListRepository
import com.cmdevs.projectunknown.view.friend.adapter.model.FriendRecyclerModel

class FriendViewModel(
    private val friendListRepository: FriendListRepository,
    private val friendRecyclerModel: FriendRecyclerModel) {

    fun getFriendInfo() {
        friendListRepository.getFriendList {
            it.forEach {
                friendRecyclerModel.addItem(it)
            }
        }
    }

}