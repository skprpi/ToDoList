package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewAdapter(val newInflater: LayoutInflater, val items: List<NewItem>):RecyclerView.Adapter<NewItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewItemViewHolder {
        return NewItemViewHolder(newInflater.inflate(R.layout.item_one, parent, false))
    }

    override fun onBindViewHolder(holder: NewItemViewHolder, position: Int) {
        holder.bin(items[position])
    }

    override fun getItemCount() = items.size


}