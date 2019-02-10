package com.cmdevs.projectunknown

import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

open class BaseActivity : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()
}