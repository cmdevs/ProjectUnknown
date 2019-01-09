package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.data.FirebaseSignInEmailStateDataSource
import com.cmdevs.projectunknown.data.FirebaseSignInStateDataSource
import com.cmdevs.projectunknown.domain.ObserveEmailAuthStateUseCase
import com.cmdevs.projectunknown.domain.ObserveUserAuthStateUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val useCaseModule = Kodein.Module("usecase_module") {
    bind<ObserveUserAuthStateUseCase>() with singleton {
        ObserveUserAuthStateUseCase(
            instance<FirebaseSignInStateDataSource>()
        )
    }
    bind<ObserveEmailAuthStateUseCase>() with singleton {
        ObserveEmailAuthStateUseCase(
            instance<FirebaseSignInEmailStateDataSource>()
        )
    }
}