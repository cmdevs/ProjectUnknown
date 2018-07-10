package com.cmdevs.projectunknown.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.cmdevs.projectunknown.adapters.holders.FriendViewHolder
import com.cmdevs.projectunknown.adapters.model.FriendRecyclerModel
import com.cmdevs.projectunknown.data.FriendListData

abstract class BaseAdapter(
    val context: Context?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    FriendRecyclerModel {

    private var list: List<FriendListData> = mutableListOf()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FriendViewHolder)?.onCreateViewHolder(list[position])
    }

    override fun getItem(position: Int) = list[position]

    override fun getItemCount(): Int {
        return list.size
    }

    override fun notifyDataSetChange() {
        notifyDataSetChanged()
    }

    override fun setItem(list: List<FriendListData>) {
        this@BaseAdapter.list = list
        notifyDataSetChange()
    }

}