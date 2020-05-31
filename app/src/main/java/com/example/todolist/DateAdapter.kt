package com.example.todolist

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DateAdapter() : RecyclerView.Adapter<DateAdapter.Holder>() {

    lateinit var myList: List<DateItem>

    fun setItems(list : List<DateItem>){
        myList = list
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weekDay = itemView.findViewById<TextView>(R.id.day_of_week)
        val monthDay = itemView.findViewById<TextView>(R.id.day_of_week)

        fun bind(dateItem: DateItem) {
            weekDay.text = dateItem.week
            monthDay.text = dateItem.month
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("Not yet implemented")
    }
}