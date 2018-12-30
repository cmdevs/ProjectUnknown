package com.cmdevs.projectunknown.data

interface EmailUserInfo

data class EmailSignInUserInfo(
    val eamilId: String,
    val emailPassword: String
) : EmailUserInfo

data class EmailRegisterUserInfo(
    val emailId: String,
    val emailPassword: String
) : EmailUserInfo
