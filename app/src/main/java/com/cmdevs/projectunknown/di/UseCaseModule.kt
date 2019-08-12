package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.domain.auth.ObserveUserAuthStateUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val useCaseModule = Kodein.Module("useCaseModule") {
    bind<ObserveUserAuthStateUseCase>() with provider { ObserveUserAuthStateUseCase(instance()) }
}