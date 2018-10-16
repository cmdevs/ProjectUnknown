package com.cmdevs.projectunknown.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey @ColumnInfo(name = "email") val email: String,
    val name: String?,
    val message: String?,
    val photoUrl: String?
) :  Serializable