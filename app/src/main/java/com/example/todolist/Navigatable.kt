package com.example.todolist

interface Navigatable {

    enum class Screens{
        ITEM_LIST,
        DETAIL_SCREEN
    }

    fun navigateTo(screen :Screens)

    fun goBack()

}