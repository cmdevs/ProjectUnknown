package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.ui.FirebaseLoginViewModelDelegate
import com.cmdevs.projectunknown.ui.LoginViewModelDelegate
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val delegateModule = Kodein.Module("delegateModule") {
    bind<LoginViewModelDelegate>() with provider { FirebaseLoginViewModelDelegate(instance()) }
}