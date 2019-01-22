package com.cmdevs.projectunknown.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvder(
    provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

inline fun <reified VM : ViewModel> Fragment.viewModelProvder(
    provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

inline fun <reified VM : ViewModel> Fragment.activityViewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(requireActivity(), provider).get(VM::class.java)

/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}

/** Uses `Transformations.switchMap` on a LiveData */
fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this, body)
}

fun <T1 : Any, T2 : Any, R : Any> safeLet(t: T1?, t2: T2?, block: (T1, T2) -> R): R? {
    return if (t != null && t2 != null) block(t, t2) else null
}

fun <T1 : Any, T2 : Any, R : Any> safeNotNullLet(t: T1?, t2: T2?, block: (T1?, T2?) -> R){
    if(t != null && t2 != null) block(t, t2)
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.()-> FragmentTransaction) {
    beginTransaction().func().commit()
}

inline fun BottomNavigationView.setupWithNavController(navController: NavController) {
    NavigationUI.setupWithNavController(this, navController)
}