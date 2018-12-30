package com.cmdevs.projectunknown.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.cmdevs.projectunknown.BaseActivity
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.data.UserAuthInfo
import com.cmdevs.projectunknown.databinding.ActivityLoginBinding
import com.cmdevs.projectunknown.result.EventObserver
import com.cmdevs.projectunknown.ui.dialog.JoinInEmailDialog
import com.cmdevs.projectunknown.ui.dialog.SignInEmailDialog
import com.cmdevs.projectunknown.util.signin.SignInEvent
import com.cmdevs.projectunknown.util.signin.SignInHandler
import com.cmdevs.projectunknown.util.viewModelProvder
import org.kodein.di.generic.instance
import java.io.Serializable

class LoginActivity : BaseActivity() {

    val factory by instance<LoginViewModelProviderFactory>()
    val signInHandler by instance<SignInHandler>()

    lateinit var loginViewModel: LoginViewModel

    companion object {
        const val REQUEST_GOOGLE_SIGNING = 1000
        const val REQUEST_FACEBOOK_SIGNING = 2000

        fun startIntent(context: Context, userAuthInfo: UserAuthInfo): Intent {
            return Intent(context, ProfileActivity::class.java).apply {
                putExtra("userAuthInfo", userAuthInfo as Serializable)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = provideViewModel()

        DataBindingUtil.setContentView<ActivityLoginBinding>(
            this,
            R.layout.activity_login
        ).run {
            setLifecycleOwner(this@LoginActivity)
            viewmodel = loginViewModel
        }

        loginViewModel.performSignInEvent.observe(this, EventObserver {
            when (it) {
                SignInEvent.GOOGLE -> {
                    signInHandler.makeSignIntent(
                        SignInEvent.GOOGLE
                    )?.let { startActivityForResult(it, REQUEST_GOOGLE_SIGNING) }
                }

                SignInEvent.FACEBOOK -> {
                    signInHandler.makeSignIntent(
                        SignInEvent.FACEBOOK
                    )?.let { startActivityForResult(it, REQUEST_FACEBOOK_SIGNING) }
                }

                SignInEvent.EMAIL_SIGN_IN -> {
                    signInHandler.onViewCreateEmailSignIn(
                        SignInEmailDialog.newInstance()
                    ) {
                        show(
                            supportFragmentManager,
                            "EMAIL_SIGN_IN"
                        )
                    }
                }

                SignInEvent.EMAIL_JOIN_IN -> {
                    signInHandler.onViewCreateEmailSignIn(
                        JoinInEmailDialog.newInstance()
                    ) {
                        show(
                            supportFragmentManager,
                            "EMAIL_JOIN_IN"
                        )
                    }
                }
            }
        })

        loginViewModel.currentAuthUser.observe(this, EventObserver {
            Log.d("cylee", "state boolean : ${it}")
            //TODO -> move profile
            startActivity(startIntent(this@LoginActivity, it))
        })

        loginViewModel.errorMessage.observe(this, EventObserver {
            Log.d("cylee", "state exception : ${it}")
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })

        /**
         * todolist
         * 아래의 옵저브를 제거하기 위해
         * 통합 livedata 구축이 필요함.
         * google, facebook, email이 하나의 라이브 데이터를 통해서,
         * 가지치기를 하고 통합적인 데이터가 한대모여 액티비티에서 활용.
         **/
        loginViewModel.isLoading.observe(this, Observer {

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun provideViewModel(): LoginViewModel = viewModelProvder(factory)
}