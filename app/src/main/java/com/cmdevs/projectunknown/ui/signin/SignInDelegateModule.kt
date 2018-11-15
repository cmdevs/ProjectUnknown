package com.cmdevs.projectunknown.ui.signin

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val signInDelegateModule = Kodein.Module("signin_delegate_module") {

    bind<SignInDelegate>() with singleton { FirebaseSignInlDelegate(instance()) }
}
