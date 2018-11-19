package com.cmdevs.projectunknown

import android.app.Application
import com.cmdevs.projectunknown.di.domainModule
import com.cmdevs.projectunknown.di.fireBaseModule
import com.cmdevs.projectunknown.di.signInModule
import com.cmdevs.projectunknown.di.viewModelFactoryModule
import com.cmdevs.projectunknown.ui.signin.signInDelegateModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class MainApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MainApplication))
        import(fireBaseModule)
        import(domainModule)
        import(signInModule)
        import(signInDelegateModule)
        import(viewModelFactoryModule)
    }

}