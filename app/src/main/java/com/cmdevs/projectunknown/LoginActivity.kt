package com.cmdevs.projectunknown

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.cmdevs.projectunknown.databinding.ActivityLoginBinding
import com.cmdevs.projectunknown.ui.dialog.BottomSheetDialog
import com.cmdevs.projectunknown.util.bottomSheetShow
import com.cmdevs.projectunknown.util.obtainViewModel
import com.cmdevs.projectunknown.viewmodels.LoginViewModel
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel

    companion object {
        const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel()
        bindView()
        setupFacebook()
        subscribeUi()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, data)
    }

    fun bindView() {
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
            .run {
                //clickListener = this@LoginActivity.clickListener
                viewmodel = this@LoginActivity.viewModel
            }
    }

    fun setupFacebook() {
        facebookSignButton.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this, mutableListOf("email", "public_profile"))
            viewModel.setupFacebook()
        }
    }

    fun subscribeUi() {
        viewModel.apply {
            getGoogleSignEvent().observe(this@LoginActivity, Observer {
                it?.let { startActivityForResult(it, RC_SIGN_IN) }
            })

            getSendUserTaskEvent().observe(this@LoginActivity, Observer {
                it?.let { startActivity(it) }
            })

            emailEvent.observe(this@LoginActivity, Observer {
                bottomSheetShow(
                    BottomSheetDialog.newInstance(),
                    "bottomSheet"
                )
            })

            loadingDialogEvent.observe(this@LoginActivity, Observer {
                it?.let {
                    if(it) showLoading() else hideLoading()
                }
            })
        }
    }

    fun obtainViewModel(): LoginViewModel = obtainViewModel(viewModelFactory)

}