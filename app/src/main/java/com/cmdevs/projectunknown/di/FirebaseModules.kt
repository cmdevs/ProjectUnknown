package com.cmdevs.projectunknown.di

import android.app.Application
import android.support.v7.app.AlertDialog
import com.cmdevs.projectunknown.R
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModules(val application: Application) {

    @Singleton
    @Provides
    fun provideApplication() = application

    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideCallbackManager() = CallbackManager.Factory.create()

    @Singleton
    @Provides
    fun provideGoogleConfig(context: Application) =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(application.applicationContext.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

}