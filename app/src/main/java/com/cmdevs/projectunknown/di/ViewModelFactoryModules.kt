package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.ui.login.LoginViewModelProviderFactory
import com.cmdevs.projectunknown.ui.profile.ProfileViewModelProviderFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val viewModelFactoryModule = Kodein.Module("view_model_provider_factory_module") {

    bind<LoginViewModelProviderFactory>() with singleton {
        LoginViewModelProviderFactory(
            instance()
        )
    }
    bind<ProfileViewModelProviderFactory>() with singleton {
        ProfileViewModelProviderFactory()
    }
}