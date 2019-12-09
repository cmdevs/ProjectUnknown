package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.ui.LoginViewModelFactory
import com.cmdevs.projectunknown.ui.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val viewModelFactoryModule = Kodein.Module("viewModelFactoryModule") {
    bind<LoginViewModelFactory>() with singleton {
        LoginViewModelFactory(instance())
    }
    bind<ProfileViewModelFactory>() with singleton {
        ProfileViewModelFactory(instance(), instance())
    }
}