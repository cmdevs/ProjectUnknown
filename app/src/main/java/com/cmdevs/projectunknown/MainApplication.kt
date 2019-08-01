package com.cmdevs.projectunknown

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class MainApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(androidXModule(this@MainApplication))
    }

    override fun onCreate() {
        super.onCreate()
    }
}