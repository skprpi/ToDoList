package com.example.todolist

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.abs
import java.util.*
import java.util.concurrent.Executors

typealias OnMonthDayClickListener = (Int)->Unit//замена интерфейса
class DateAdapter() : RecyclerView.Adapter<DateAdapter.Holder>() {

    lateinit var lastHolder: Holder
    var boolHolder = false

    lateinit var myList: MutableList<DateItem>

    lateinit var listener: OnMonthDayClickListener

    fun setItems(list : MutableList<DateItem>){
        myList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder { // add all--------------------------------------------
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_date, parent, false)
        val holder = Holder(view)

        view.setOnClickListener{
            listener(holder.adapterPosition)

            if (!boolHolder){
                holder.parents2.setBackgroundResource(R.drawable.black_rounded_bg)
            } else{
                holder.parents2.setBackgroundResource(R.drawable.black_rounded_bg)
                lastHolder.parents2.setBackgroundResource(R.drawable.gray_rounded_bg)
            }
            boolHolder = true
            lastHolder = holder
        }
        return holder
    }

    fun setClickListener(listener: OnMonthDayClickListener){
        this.listener = listener
    }

    override fun getItemCount() = myList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(myList[position], position)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var buttonNormalBg = R.drawable.gray_rounded_bg
        private var buttonActiveBg = R.drawable.orange_rounded_bg

        val weekDay = itemView.findViewById<TextView>(R.id.day_of_week)
        val monthDay = itemView.findViewById<TextView>(R.id.day_of_moth)
        val parents = itemView.findViewById<ConstraintLayout>(R.id.parents_layout)
        val parents2 = itemView.findViewById<ConstraintLayout>(R.id.parents_layout2)
        val indicator = itemView.findViewById<TextView>(R.id.indicator)
        val monthDate = itemView.findViewById<TextView>(R.id.month_text)


        fun bind(dateItem: DateItem, pos: Int) {
            weekDay.text = dateItem.week// день недели
            monthDay.text = dateItem.month//число
            if (dateItem.month == "1"){
                monthDate.setText(dateItem.nameMonth)
                monthDate.visibility = View.VISIBLE
            } else{
                monthDate.visibility = View.GONE
            }

            Executors.newSingleThreadExecutor().execute {
                if (ItemRepository.newInstance(itemView.context).getCountItems(dateItem.week) > 0) {
                    indicator.visibility = View.VISIBLE
                } else {
                    indicator.visibility = View.GONE
                }
            }



            if (pos == 180){
                parents.setBackgroundResource(buttonActiveBg)
                monthDay.setTextColor(Color.WHITE)
                weekDay.setTextColor(Color.WHITE)
                parents2.setBackgroundResource(buttonActiveBg)
            } else{
                parents.setBackgroundResource(buttonNormalBg)
                monthDay.setTextColor(Color.BLACK)
                weekDay.setTextColor(Color.BLACK)
                parents2.setBackgroundResource(buttonNormalBg)
            }
        }
    }

}