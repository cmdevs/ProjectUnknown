package com.cmdevs.projectunknown

import android.arch.lifecycle.MutableLiveData
import org.junit.Test

import org.junit.Assert.*
import org.junit.rules.Timeout.millis


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val boolean = false

    @Test
    fun test() {
        if (!boolean) println("true") else println("false")
    }

}

class GenericClass {

    val list = mutableListOf<Generic<String>>()

    fun onCreate() {
        list.add(object : Generic<String>{
            override fun setItem(test: String): Boolean {
                return false
            }
        })
        list.add(object : Generic<String>{
            override fun setItem(test: String): Boolean {
                return false
            }
        })

        onlyReadItem(list, object : Generic<Generic<String>>{
            override fun setItem(test: Generic<String>): Boolean {
                return test.setItem("")
            }
        })
    }

    fun onlyReadItem(list: MutableList<Generic<String>>, filter: Generic<in Generic<String>>){
        for((index) in list.withIndex()) {
            if(filter.setItem(list.get(index))) {

            }
        }
    }
}

interface Generic<T> {
    fun setItem(test: T): Boolean
}





