package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.App
import com.cmdevs.projectunknown.BaseActivity
import com.cmdevs.projectunknown.LoginActivity
import com.cmdevs.projectunknown.di.viewmodels.ViewModelModules
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FirebaseModules::class, ViewModelModules::class])
interface FirebaseComponent {
    fun inject(application: App)
    fun inject(baseActivity: BaseActivity)

    //fun inject(loginActivity: LoginActivity)
}
