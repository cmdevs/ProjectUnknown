package com.cmdevs.projectunknown

import android.app.Application
import com.cmdevs.projectunknown.di.DaggerFirebaseComponent
import com.cmdevs.projectunknown.di.FirebaseComponent
import com.cmdevs.projectunknown.di.FirebaseModules

class App : Application() {

    val daggerFirebaseComponent: FirebaseComponent by lazy {
        DaggerFirebaseComponent
            .builder()
            .firebaseModules(FirebaseModules(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.daggerFirebaseComponent.inject(this)
    }

}