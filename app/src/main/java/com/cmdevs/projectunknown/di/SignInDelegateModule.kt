package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.ui.FirebaseSignInViewModelDelegate
import com.cmdevs.projectunknown.ui.SignInViewModelDelegate
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val signInDelegateModule = Kodein.Module("signin_delegate_module") {
    bind<SignInViewModelDelegate>() with singleton {
        FirebaseSignInViewModelDelegate(
            instance(),
            instance()
        )
    }
}
