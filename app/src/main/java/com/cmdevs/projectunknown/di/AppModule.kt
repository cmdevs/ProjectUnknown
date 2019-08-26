package com.cmdevs.projectunknown.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton


val appModule = Kodein.Module("appModule") {
    bind<FirebaseAuth>() with singleton { FirebaseAuth.getInstance() }
    bind<FirebaseFirestore>() with singleton { FirebaseFirestore.getInstance() }
}