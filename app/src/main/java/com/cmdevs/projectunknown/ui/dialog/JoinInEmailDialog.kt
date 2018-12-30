package com.cmdevs.projectunknown.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdevs.projectunknown.databinding.DialogEmailJoinInBinding
import com.cmdevs.projectunknown.ui.LoginActivity
import com.cmdevs.projectunknown.ui.LoginViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class JoinInEmailDialog
    : BottomSheetDialogFragment() {

    lateinit var loginViewModel: LoginViewModel

    companion object {
        fun newInstance() = JoinInEmailDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = (requireActivity() as LoginActivity).provideViewModel()

        val binding = DialogEmailJoinInBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            viewmodel = loginViewModel
        }

        return binding.root
    }
}