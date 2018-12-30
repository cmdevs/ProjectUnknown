package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.data.FirebaseSignInEmailStateDataSource
import com.cmdevs.projectunknown.data.FirebaseSignInStateDataSource
import com.cmdevs.projectunknown.data.SignInStateDataSource
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val dataSourceModule = Kodein.Module("data_source_module") {
    bind<SignInStateDataSource>() with singleton {
        FirebaseSignInStateDataSource(
            instance()
        )
        FirebaseSignInEmailStateDataSource(
            instance()
        )
    }
}