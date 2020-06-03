package com.example.todolist

import android.graphics.Color


class ItemRepository {

    private var listItems = mutableListOf(
        Task(0,"Example", "подзаголовок", MutableList<Boolean>(7) {false}, -1,-1,-1,-1)
    )

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

    fun getItems() = listItems

    fun addItem(item: Task){
        listItems.add(item)
    }

    fun removeItem(item: Task){
        listItems.remove(item)
    }



    fun newInstance(): ItemRepository{
        return instance
    }

    companion object {
        val instance = ItemRepository()
    }
}