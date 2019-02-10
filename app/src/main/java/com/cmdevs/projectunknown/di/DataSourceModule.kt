package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.data.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val dataSourceModule = Kodein.Module("data_source_module") {
    bind<FirebaseSignInStateDataSource>() with singleton {
        FirebaseSignInStateDataSource(
            instance()
        )
    }

    bind<FirebaseSignInEmailStateDataSource>() with singleton {
        FirebaseSignInEmailStateDataSource(
            instance()
        )
    }

    bind<ProfileDataSource>() with singleton {
        FireStoreProfileDataSource()
    }

    bind<FireBaseProfileDataRepository>() with singleton {
        FireBaseProfileDataRepository(
            instance()
        )
    }
}