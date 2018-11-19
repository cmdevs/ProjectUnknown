package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.data.signin.datasource.AuthStateUserDataSource
import com.cmdevs.projectunknown.data.signin.datasource.FirebaseAuthStateUserDataSource
import com.cmdevs.projectunknown.data.signin.datasource.FirestoreRegisteredUserDataSource
import com.cmdevs.projectunknown.data.signin.datasource.RegisteredUserDataSource
import com.cmdevs.projectunknown.ui.signin.FirebaseSignInlDelegate
import com.cmdevs.projectunknown.ui.signin.SignInDelegate
import com.cmdevs.projectunknown.util.signin.DefaultSignInHandler
import com.cmdevs.projectunknown.util.signin.SignInHandler
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val signInModule = Kodein.Module("signin_module") {

    bind<SignInHandler>() with singleton { DefaultSignInHandler() }
    bind<RegisteredUserDataSource>() with singleton { FirestoreRegisteredUserDataSource() }
    bind<AuthStateUserDataSource>() with singleton { FirebaseAuthStateUserDataSource(instance()) }
}