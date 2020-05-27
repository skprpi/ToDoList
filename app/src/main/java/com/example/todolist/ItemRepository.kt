package com.example.todolist

import android.graphics.Color


class ItemRepository {

    private var listItems = mutableListOf(
        Task(0,"Заголовок1", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1) ,
        Task(1,"Заголовок2", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1) ,
        Task(2,"Заголовок3", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1) ,
        Task(3,"Заголовок4", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1) ,
        Task(4,"Заголовок5", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1) ,
        Task(5,"Заголовок6", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1) ,
        Task(6,"Заголовок1", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1) ,
        Task(7,"Заголовок2", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1) ,
        Task(8,"Заголовок3", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1) ,
        Task(9,"Заголовок4", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1) ,
        Task(10,"Заголовок5", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1) ,
        Task(11,"Заголовок6", "подзаголовок", emptyList<Int>(), -1,-1,-1,-1)
    )

    fun updateTask(task: Task){
        for (item in listItems) {
            if (item.id == task.id) {
                item.copy(titleText = task.titleText, subtitleText = task.subtitleText)
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