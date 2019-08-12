package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.data.AuthStateUserDataSource
import com.cmdevs.projectunknown.data.FirebaseAuthStateUserDataSource
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val repositoryModule = Kodein.Module("repositoryModule") {
    bind<AuthStateUserDataSource>() with provider { FirebaseAuthStateUserDataSource(instance()) }
}