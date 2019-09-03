package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.data.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val repositoryModule = Kodein.Module("repositoryModule") {
    bind<AuthStateUserDataSource>() with provider {
        FirebaseAuthStateUserDataSource(
            instance(),
            instance()
        )
    }
    bind<RegisteredUserDataSource>() with provider { FirestoreRegisteredUserDataSource(instance()) }
    bind<AuthTokenUpdater>() with provider { FirebaseAuthTokenUpdater(instance()) }
}