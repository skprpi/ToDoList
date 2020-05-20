package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class EditTaskFragment:  Fragment() {

    lateinit var repository: ItemRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        repository = ItemRepository.instance

        //добавить поля, кнопка сохранить (add item) -> репозиторий

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        return view
    }
}