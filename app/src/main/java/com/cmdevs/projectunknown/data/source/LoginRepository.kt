package com.cmdevs.projectunknown.data.source

import com.cmdevs.projectunknown.data.source.local.LoginDao


class LoginRepository(
    private val loginDao: LoginDao
) {

    companion object {
        private var INSTANCE: LoginRepository? = null

        fun getInstance(loginDao: LoginDao) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: LoginRepository(loginDao).also { INSTANCE = it }
        }
    }


}