package com.cmdevs.projectunknown

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.cmdevs.projectunknown.util.makeLoadingDialog
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    private val loadingDialog: AlertDialog by lazy {
        makeLoadingDialog(this)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).daggerFirebaseComponent.inject(this)
    }

    fun showLoading() {
        Log.d("cylee","showLoading()")
        loadingDialog.takeUnless { it.isShowing }?.let { it.show() }
//        loadingDialog.show()
    }

    fun hideLoading() {
        Log.d("cylee","hideLoading()")
//        loadingDialog.dismiss()
        loadingDialog.takeIf { it.isShowing }?.let { it.dismiss() }
    }

}