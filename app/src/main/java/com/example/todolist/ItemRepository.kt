package com.example.todolist

import android.graphics.Color


class ItemRepository {

    private val listItems = mutableListOf(
        Task("Заголовок1", "подзаголовок", Color.RED) ,
        Task("Заголовок2", "подзаголовок", Color.BLUE) ,
        Task("Заголовок3", "подзаголовок", Color.BLACK) ,
        Task("Заголовок4", "подзаголовок", Color.RED) ,
        Task("Заголовок5", "подзаголовок", Color.BLUE) ,
        Task("Заголовок6", "подзаголовок", Color.BLACK)
    )

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