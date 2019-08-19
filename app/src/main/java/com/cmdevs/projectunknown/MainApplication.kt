package com.cmdevs.projectunknown

import android.app.Application
import com.cmdevs.projectunknown.di.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class MainApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(androidXModule(this@MainApplication))
        import(appModule)
        import(repositoryModule)
        import(viewModelFactoryModule)
        import(useCaseModule)
        import(delegateModule)
        import(signInModule)
    }

    override fun onCreate() {
        super.onCreate()
    }
}