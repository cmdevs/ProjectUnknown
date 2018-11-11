package com.cmdevs.projectunknown.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdevs.projectunknown.databinding.DialogLoginBottomSheetBinding
import com.cmdevs.projectunknown.ui.LoginActivity
import com.cmdevs.projectunknown.ui.LoginViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LoginBottomSheetDialog
    : BottomSheetDialogFragment() {

    lateinit var loginViewModel: LoginViewModel

    companion object {
        fun newInstance() = LoginBottomSheetDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = (requireActivity() as LoginActivity).provideViewModel()
        val bind = DialogLoginBottomSheetBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            viewmodel = loginViewModel
        }
        return bind.root
    }
}