package com.cmdevs.projectunknown.di

import com.google.firebase.auth.FirebaseAuth
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton


val firebaseAuthModule = Kodein.Module("firebaseAuthModule") {
    bind<FirebaseAuth>() with singleton { FirebaseAuth.getInstance() }
}