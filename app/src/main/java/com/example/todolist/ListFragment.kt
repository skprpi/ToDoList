package com.example.todolist

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment: Fragment() {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_layout_setting, container, false)//Переводит xml в код (все элементы)

        recycler = view.findViewById(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(context)//отвечает за расположение элементов на экране внутри recycler


        val listener = object: ListenerInterface{
            override fun onItemClicked(item: Task) {
                if (activity is Navigatable){
                    (activity as Navigatable).navigateTo(Navigatable.Screens.DETAIL_SCREEN)
                }

            }
        }

        val adapter = NewAdapter(ItemRepository.instance.getItems(), listener)

        recycler.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}