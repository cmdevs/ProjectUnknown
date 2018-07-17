package com.cmdevs.projectunknown.data.source.local

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.cmdevs.projectunknown.data.Friend

@Dao
interface FriendDao {
    @Query("SELECT * FROM Friends")
    fun getFriends(): DataSource.Factory<Int, Friend>

    @Insert
    fun addFriend(friend: Friend)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(friends: List<Friend>)
}