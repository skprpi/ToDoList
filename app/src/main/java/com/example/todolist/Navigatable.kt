package com.example.todolist

interface Navigatable {

    enum class Screens{
        ITEM_LIST,
        DETAIL_SCREEN
    }

    fun goBack()

    fun navigateTo(screen: Screens, data: Any? = null)
}