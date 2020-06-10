package com.example.todolist

interface ListenerInterface {
    fun onItemClicked(item: Task)

    fun onEmptyList()
}