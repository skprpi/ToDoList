package com.example.todolist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val items = mutableListOf(
        NewItem("Заголовок1", "подзаголовок", Color.RED) ,
        NewItem("Заголовок2", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок3", "подзаголовок", Color.BLACK) ,
        NewItem("Заголовок4", "подзаголовок", Color.RED) ,
        NewItem("Заголовок5", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок6", "подзаголовок", Color.BLACK) ,
        NewItem("Заголовок7", "подзаголовок", Color.RED) ,
        NewItem("Заголовок8", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок9", "подзаголовок", Color.BLACK) ,
        NewItem("Заголовок10", "подзаголовок", Color.RED) ,
        NewItem("Заголовок11", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок12", "подзаголовок", Color.BLACK) ,
        NewItem("Заголовок13", "подзаголовок", Color.RED) ,
        NewItem("Заголовок14", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок15", "подзаголовок", Color.BLACK) ,
        NewItem("Заголовок16", "подзаголовок", Color.RED) ,
        NewItem("Заголовок17", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок18", "подзаголовок", Color.BLACK)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
        clickListenerAddandRemove()
    }

    fun initRecycler(){

        val listenerInterface: ListenerInterface = object : ListenerInterface{
            override fun onItemClicked(item: NewItem) {
                val intent = Intent(this@MainActivity, ActivityList::class.java)
                startActivity(intent)
            }
        }
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = NewAdapter(items, listenerInterface)
    }

    fun clickListenerAddandRemove(){
        findViewById<View>(R.id.addButton).setOnClickListener(){
            if (items.size > 0) {
                items.add(items.indexOf(items.last()), NewItem("header1", "subHeader", Color.CYAN))
            } else{
                items.add(0, NewItem("header1", "subHeader", Color.CYAN))
            }
            recycler.adapter?.notifyDataSetChanged()
        }

        findViewById<View>(R.id.removeButton).setOnClickListener(){
            if (items.size > 0) {
                items.removeAt(items.lastIndexOf(items.last()))
                recycler.adapter?.notifyDataSetChanged()
            }
        }
    }

}
