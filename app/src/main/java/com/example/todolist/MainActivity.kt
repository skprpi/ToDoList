package com.example.todolist

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val items = mutableListOf(
        NewItem("Заголовок1", "подзаголовок", Color.RED) ,
        NewItem("Заголовок2", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок3", "подзаголовок", Color.BLACK) ,
        NewItem("Заголовок1", "подзаголовок", Color.RED) ,
        NewItem("Заголовок2", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок3", "подзаголовок", Color.BLACK) ,
        NewItem("Заголовок1", "подзаголовок", Color.RED) ,
        NewItem("Заголовок2", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок3", "подзаголовок", Color.BLACK) ,
        NewItem("Заголовок1", "подзаголовок", Color.RED) ,
        NewItem("Заголовок2", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок3", "подзаголовок", Color.BLACK) ,
        NewItem("Заголовок1", "подзаголовок", Color.RED) ,
        NewItem("Заголовок2", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок3", "подзаголовок", Color.BLACK) ,
        NewItem("Заголовок1", "подзаголовок", Color.RED) ,
        NewItem("Заголовок2", "подзаголовок", Color.BLUE) ,
        NewItem("Заголовок3", "подзаголовок", Color.BLACK)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
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
}
