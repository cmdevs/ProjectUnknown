package com.cmdevs.projectunknown.util.signin

sealed class SignInResult
object SignInSuccess : SignInResult()
//data class SignInFailed(val error: FirebaseUiException?) : SignInResult()
