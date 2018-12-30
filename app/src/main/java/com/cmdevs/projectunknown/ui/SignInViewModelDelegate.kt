package com.cmdevs.projectunknown.ui

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.data.EmailRegisterUserInfo
import com.cmdevs.projectunknown.data.EmailSignInUserInfo
import com.cmdevs.projectunknown.data.EmailUserInfo
import com.cmdevs.projectunknown.data.UserAuthInfo
import com.cmdevs.projectunknown.domain.ObserveEmailAuthStateUseCase
import com.cmdevs.projectunknown.domain.ObserveUserAuthStateUseCase
import com.cmdevs.projectunknown.result.Event
import com.cmdevs.projectunknown.result.Result
import com.cmdevs.projectunknown.util.safeLet
import com.cmdevs.projectunknown.util.signin.EmailEvent
import com.cmdevs.projectunknown.util.signin.SignInEvent

interface SignInViewModelDelegate {

    val currentUser: LiveData<Result<UserAuthInfo>>
    val currentEmailUser: LiveData<Result<UserAuthInfo>>
    val performSignInEvent: MutableLiveData<Event<SignInEvent>>

    val currentEmailUserId: MutableLiveData<String>
    val currentEmailUserPassword: MutableLiveData<String>
    //val currentEmailUserId: ObservableField<String>
    //val currentEmailUserPassword: ObservableField<String>

    fun <T> requestSignIn(event: Event<T>)
    fun requestEmailSignIn()
    fun requestEmailJoinIn()
    fun makeEmailUserInfo(emailEvent: EmailEvent): EmailUserInfo?
    fun isSignIn(): Boolean
}

internal class FirebaseSignInViewModelDelegate(
    observeUserAuthStateUseCase: ObserveUserAuthStateUseCase,
    private val observeEmailAuthStateUseCase: ObserveEmailAuthStateUseCase
) : SignInViewModelDelegate {
    override val currentEmailUser: LiveData<Result<UserAuthInfo>>
    override val currentUser: LiveData<Result<UserAuthInfo>>
    override val performSignInEvent = MutableLiveData<Event<SignInEvent>>()

    override val currentEmailUserId: MutableLiveData<String> = MutableLiveData()
    override val currentEmailUserPassword: MutableLiveData<String> = MutableLiveData()

    init {
        currentUser = observeUserAuthStateUseCase.observe()
        currentEmailUser = observeEmailAuthStateUseCase.observe()

        observeUserAuthStateUseCase.execute(Unit)
    }

    override fun <T> requestSignIn(event: Event<T>) {
        performSignInEvent.postValue((event as Event<SignInEvent>))
    }

    override fun requestEmailSignIn() {
        makeEmailUserInfo(EmailEvent.SIGN_IN)?.let {
            observeEmailAuthStateUseCase.execute(it)
        }
    }

    override fun requestEmailJoinIn() {
        makeEmailUserInfo(EmailEvent.JOIN_IN)?.let {
            observeEmailAuthStateUseCase.execute(it)
        }
    }

    override fun makeEmailUserInfo(emailEvent: EmailEvent): EmailUserInfo? = when (emailEvent) {
        EmailEvent.SIGN_IN -> {
            Log.d("cylee", "signin")
            Log.d("cylee", "currentEmailUserId.value : ${currentEmailUserId.value}")
            Log.d("cylee", "currentEmailUserPassword.value ${currentEmailUserPassword.value}")

            safeLet(
                currentEmailUserId.value,
                currentEmailUserPassword.value
            ) { id, pwd ->
                (EmailSignInUserInfo(
                    id,
                    pwd
                ) as EmailUserInfo)
            }
        }
        EmailEvent.JOIN_IN -> {
            Log.d("cylee", "joinin")
            Log.d("cylee", "currentEmailUserId.value : ${currentEmailUserId.value}")
            Log.d("cylee", "currentEmailUserPassword.value ${currentEmailUserPassword.value}")
            safeLet(
                currentEmailUserId.value,
                currentEmailUserPassword.value
            ) { id, pwd ->
                EmailRegisterUserInfo(
                    id,
                    pwd
                ) as EmailUserInfo
            }
        }
    }

    override fun isSignIn(): Boolean {
        return (currentUser.value as? Result.Success)?.data?.isSignIn() == true
    }
}