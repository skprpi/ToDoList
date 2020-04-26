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
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = layoutManager
        recycler.adapter = NewAdapter(LayoutInflater.from(this), items)
    }
}
