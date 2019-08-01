package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.ui.LoginViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val viewModelFactoryModules = Kodein.Module("viewModelFactory") {
    bind<LoginViewModelFactory>() with provider { LoginViewModelFactory(kodein) }
}
