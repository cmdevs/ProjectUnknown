package com.cmdevs.projectunknown

import android.app.Application
import com.cmdevs.projectunknown.di.appModule
import com.cmdevs.projectunknown.di.repositoryModule
import com.cmdevs.projectunknown.di.useCaseModule
import com.cmdevs.projectunknown.di.viewModelFactoryModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class MainApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(androidXModule(this@MainApplication))
        import(appModule)
        import(repositoryModule)
        import(useCaseModule)
        import(viewModelFactoryModule)
    }

    override fun onCreate() {
        super.onCreate()
    }
}