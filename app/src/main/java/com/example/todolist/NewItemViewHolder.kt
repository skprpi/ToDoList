package com.example.todolist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title:TextView = itemView.findViewById(R.id.title2)
    val subtitle: TextView = itemView.findViewById(R.id.subtitle)
    val image: ImageView = itemView.findViewById(R.id.image2)

    fun bin(titleText: String, subtitleText: String, color: Int){
        title.text = titleText
        subtitle.text = subtitleText
        image.setBackgroundColor(color)
    }
}