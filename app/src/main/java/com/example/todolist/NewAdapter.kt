package com.example.todolist

import android.net.sip.SipSession
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewAdapter(val items: List<NewItem>, val listener: ListenerInterface):RecyclerView.Adapter<NewItemViewHolder>() {

    companion object{
        const val TAG = "NewAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_one, parent, false)
        val holder = NewItemViewHolder(view)

        view.setOnClickListener{
            val itemPosition = (parent as RecyclerView).getChildLayoutPosition(view)
            listener.onItemClicked(items[itemPosition])
        }

        return holder
    }

    override fun onBindViewHolder(holder: NewItemViewHolder, position: Int) {
        holder.bin(items[position])
    }

    override fun getItemCount() = items.size


}