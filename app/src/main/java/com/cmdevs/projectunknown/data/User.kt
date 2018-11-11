package com.cmdevs.projectunknown.data

import android.net.Uri
import java.io.Serializable

data class User(
    val email: String?,
    val name: String?,
    val message: String?,
    val photo: String?
) : Serializable