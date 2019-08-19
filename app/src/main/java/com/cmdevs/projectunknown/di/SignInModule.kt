package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.util.DefaultSignInHanlder
import com.cmdevs.projectunknown.util.SignInHandler
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val signInModule = Kodein.Module("signInModule") {
    bind<SignInHandler>() with provider { DefaultSignInHanlder() }
}