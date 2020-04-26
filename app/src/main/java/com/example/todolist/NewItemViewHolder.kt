package com.example.todolist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title:TextView = itemView.findViewById(R.id.title2)
    val subtitle: TextView = itemView.findViewById(R.id.subtitle)
    val image: ImageView = itemView.findViewById(R.id.image2)

    fun bin(item: NewItem){
        title.text = item.titleText
        subtitle.text = item.subtitleText
        image.setBackgroundColor(item.color)
    }
}