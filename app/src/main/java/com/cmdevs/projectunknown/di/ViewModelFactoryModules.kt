package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.ui.LoginViewModelProviderFactory
import com.cmdevs.projectunknown.ui.ProfileViewModelProviderFactory
import com.cmdevs.projectunknown.ui.signin.SignInDelegate
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val viewModelFactoryModule = Kodein.Module("view_model_provider_factory_module") {
    bind<LoginViewModelProviderFactory>() with singleton {
        LoginViewModelProviderFactory(
            instance<SignInDelegate>()
        )
    }

    bind<ProfileViewModelProviderFactory>() with singleton {
        ProfileViewModelProviderFactory()
    }
}