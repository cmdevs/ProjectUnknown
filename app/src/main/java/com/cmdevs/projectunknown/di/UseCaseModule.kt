package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.domain.ObserveFacebookAcceseTokenUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val useCaseModule = Kodein.Module("usecase_module") {
    bind<ObserveFacebookAcceseTokenUseCase>() with singleton {
        ObserveFacebookAcceseTokenUseCase(
            instance()
        )
    }
}