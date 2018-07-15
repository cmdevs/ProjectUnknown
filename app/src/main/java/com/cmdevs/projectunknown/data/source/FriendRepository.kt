package com.cmdevs.projectunknown.data.source

import com.cmdevs.projectunknown.data.Friend
import com.cmdevs.projectunknown.data.source.local.FriendDao

class FriendRepository(
    private val friendDao: FriendDao
) {

    fun getFriends() = friendDao.getFriends()

    companion object {

        private var INSTANCE: FriendRepository? = null

        fun getInstance(friendDao: FriendDao) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: FriendRepository(friendDao).also { INSTANCE = it }
        }

    }

}