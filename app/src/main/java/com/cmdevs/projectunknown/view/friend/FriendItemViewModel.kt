package com.cmdevs.projectunknown.view.friend

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.Observable
import android.databinding.ObservableField
import com.cmdevs.projectunknown.data.FriendListData
import com.cmdevs.projectunknown.data.friend.FriendListRepository

class FriendItemViewModel(val friendListRepository: FriendListRepository) : BaseObservable() {

    private val friendData: ObservableField<FriendListData> = ObservableField()
    private val name: ObservableField<String> = ObservableField()
    private val age: ObservableField<Int> = ObservableField()
    private val message: ObservableField<String> = ObservableField()

    init {
        //TODO : for friendDetail that use callback, item
        friendData.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                friendData.get()?.let {
                    name.set(it.name)
                    age.set(it.age)
                    message.set(it.message)
                } ?: let {
                    name.set("UnKnownName")
                    age.set(0)
                    message.set("")
                }
            }
        })
    }

    @Bindable
    fun getBindName() = friendData.get()?.name ?: "UnKnownName"

    @Bindable
    fun getBindAge() = friendData.get()?.age ?: 0

    @Bindable
    fun getBindMessage() = friendData.get()?.message ?: ""

    fun setFriendData(data: FriendListData) {
        friendData.set(data)
    }

}