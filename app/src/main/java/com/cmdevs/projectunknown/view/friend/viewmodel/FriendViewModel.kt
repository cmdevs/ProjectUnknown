package com.cmdevs.projectunknown.view.friend.viewmodel

import com.cmdevs.projectunknown.data.friend.FriendListRepository
import com.cmdevs.projectunknown.view.friend.adapter.model.FriendRecyclerModel

class FriendViewModel(
    private val friendListRepository: FriendListRepository,
    private val firendRecyclerModel: FriendRecyclerModel) : ViewModel{


    override fun getFriendList() {
        friendListRepository.getFriendList {
            it.forEach{



            }
        }
    }


}