package com.cmdevs.projectunknown.ui.dialog

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdevs.projectunknown.LoginActivity
import com.cmdevs.projectunknown.databinding.BottomSheetEmailBinding
import com.cmdevs.projectunknown.viewmodels.LoginViewModel

class BottomSheetDialog : BottomSheetDialogFragment() {

    lateinit var viemodel: LoginViewModel

    companion object {
        fun newInstance() = BottomSheetDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viemodel = (activity as LoginActivity).obtainViewModel()
        val binding = BottomSheetEmailBinding.inflate(inflater, container, false).apply {
            viewmodel = this@BottomSheetDialog.viemodel
        }
        return binding.root
    }

}
