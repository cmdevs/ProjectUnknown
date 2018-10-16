package com.cmdevs.projectunknown.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Friends")
data class Friend(
    @PrimaryKey @ColumnInfo(name = "id") val userId: String,
    val name: String,
    val message: String,
    val profileImage: String
)
