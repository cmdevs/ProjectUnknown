package com.cmdevs.projectunknown.di

import android.content.Context
import com.cmdevs.projectunknown.R
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val fireBaseModule = Kodein.Module("firebase_module") {

    //firebase
    bind<FirebaseAuth>() with singleton { FirebaseAuth.getInstance() }

    //google
    bind<GoogleSignInOptions>() with singleton {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(instance<Context>().getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    //facebook
    bind<CallbackManager>() with singleton { CallbackManager.Factory.create() }
}
