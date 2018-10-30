package com.cmdevs.projectunknown

import android.app.Application
import com.cmdevs.projectunknown.di.fireBaseModule
import com.cmdevs.projectunknown.di.viewModelFactoryModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.with

class MainApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        constant("test") with "testKey"
        import(androidXModule(this@MainApplication))
        import(viewModelFactoryModule)
        import(fireBaseModule)
    }

}