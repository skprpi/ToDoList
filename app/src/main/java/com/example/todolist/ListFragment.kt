package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment: Fragment() {

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: ListTaskAdapter

    lateinit var listOfDate : List<DateItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_task_list, container, false)//Переводит xml в код (все элементы)

        recycler = view.findViewById(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(context)//отвечает за расположение элементов на экране внутри recycler


        val listener = object: ListenerInterface{
            override fun onItemClicked(item: Task) {
                if (activity is Navigatable){
                    (activity as Navigatable).navigateTo(Navigatable.Screens.DETAIL_SCREEN, item)
                }

            }
        }

        adapter = ListTaskAdapter(ItemRepository.instance.getItems(), listener)

        recycler.adapter = adapter



        return view
    }

    override fun onStart() {
        super.onStart()
        //adapter.setItems(ItemRepository.instance.getItems())
    }

    fun initLIstOfDay(){
        // получаем куррент в мили сек - определяем сейчас дату, нужен день месяца и день недели, инициальзирую +- месяц
    }




}