package com.example.todolist

import android.content.Context
import android.graphics.Color
import java.util.concurrent.Executors


class ItemRepository {

    private var listItems= mutableListOf<Task>()
    private lateinit var cont: Context

    fun updateTask(task: Task){
        Application.Db.getInstance(cont)?.getTaskDao()?.update(task)
    }

    fun getItems(day: String): List<Task>{
        return Application.Db.getInstance(cont)?.getTaskDao()?.getTaskByDay(day) ?: emptyList()
    }

    fun getCountItems(day: String):Int{
        return Application.Db.getInstance(cont)?.getTaskDao()?.getCountElementInList(day) ?: 0
    }


    fun addItem(item: Task){
        Executors.newSingleThreadExecutor().execute{
            Application.Db.getInstance(cont)?.getTaskDao()?.insert(item)
        }

    }

    fun removeItem(item: Task){
        //listItems.remove(item)
        Executors.newSingleThreadExecutor().execute{
            Application.Db.getInstance(cont)?.getTaskDao()?.delete(item)
        }
    }


    companion object {
        private val instance = ItemRepository()

        fun newInstance(context: Context): ItemRepository{
            instance.cont = context
            return instance
        }
    }
}