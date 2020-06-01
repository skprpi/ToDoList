package com.example.todolist

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.abs
import java.util.*


class DateAdapter() : RecyclerView.Adapter<DateAdapter.Holder>() {

    lateinit var myList: MutableList<DateItem>

    var listIndexFront: Int = 0
    var listIndexBack: Int = 0



    fun setItems(list : MutableList<DateItem>, indexFront: Int, indexBack: Int){
        myList = list
        listIndexFront = indexFront
        listIndexBack = indexBack
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder { // add all--------------------------------------------
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_date, parent, false)
        val holder = Holder(view)

        view.setOnClickListener{
            val itemPosition = holder.adapterPosition
        }

        val itemPosition = holder.adapterPosition

        //Log.d(TAG,  itemPosition.toString());


        return holder
    }

    override fun getItemCount() = myList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(myList[position])
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weekDay = itemView.findViewById<TextView>(R.id.day_of_week)
        val monthDay = itemView.findViewById<TextView>(R.id.day_of_moth)

        fun bind(dateItem: DateItem) {
            weekDay.text = dateItem.week
            monthDay.text = dateItem.month
        }
    }



    companion object {val TAG = "HHHHHHHHH"}

}