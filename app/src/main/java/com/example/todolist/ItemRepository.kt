package com.example.todolist

import android.content.Context
import android.graphics.Color
import java.util.concurrent.Executors


class ItemRepository {

    private var listItems= mutableListOf<Task>()
    private lateinit var cont: Context

    fun updateTask(task: Task){
        for (item in listItems) {
            if (item.id == task.id) {
                item.titleText = task.titleText
                item.subtitleText = task.subtitleText
                item.notification = task.notification
                item.selectedDays = task.selectedDays
                item.notificationType = task.notificationType
                return
            }
        }
    }

    fun getItems(): List<Task>{
        return Application.Db.getInstance(cont)?.getTaskDao()?.getAll() ?: emptyList()
    }


    fun addItem(item: Task){
        Executors.newSingleThreadExecutor().execute{
            Application.Db.getInstance(cont)?.getTaskDao()?.insert(item)
        }

    }

    fun removeItem(item: Task){
        listItems.remove(item)
    }


    companion object {
        private val instance = ItemRepository()

        fun newInstance(context: Context): ItemRepository{
            instance.cont = context
            return instance
        }
    }
}