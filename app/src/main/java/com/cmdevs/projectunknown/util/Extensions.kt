package com.cmdevs.projectunknown.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.*

inline fun <reified VM : ViewModel> FragmentActivity.viewModelProivder(
        provider: ViewModelProvider.NewInstanceFactory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

inline fun <reified VM : ViewModel> Fragment.viewModelProivder(
        provider: ViewModelProvider.NewInstanceFactory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}

/** Uses `Transformations.switchMap` on a LiveData */
fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this, body)
}