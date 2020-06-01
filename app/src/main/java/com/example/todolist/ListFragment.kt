package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.abs
import java.text.DateFormat
import java.text.DateFormat.getDateInstance
import java.util.*

class ListFragment: Fragment() {

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: ListTaskAdapter
    private lateinit var adapterDate: DateAdapter

    private lateinit var listOfDate : MutableList<DateItem>

    private val dayOfWeekList =listOf(
        "ПН",
        "ВТ",
        "СР",
        "ЧТ",
        "ПТ",
        "СБ",
        "ВС"
    )

    private var lastDay = 180

    private var firstDay = -180

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
        adapter.contextMenuListener = this
        recycler.adapter = adapter



        val recyclerDate = view.findViewById<RecyclerView>(R.id.recyclerDate)
        recyclerDate.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)//отвечает за расположение элементов на экране внутри recycler

        adapterDate = DateAdapter()
        initListOfDay()
        adapterDate.setItems(listOfDate, -30, 30)

        recyclerDate.adapter = adapterDate
        recyclerDate.scrollToPosition((listOfDate.size / 2) - 1)



        return view
    }


    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(0, 0, 0, "Выполнено")
        menu.add(0, 1, 0, "Удалить")
        menu.add(0, 2, 0, "Редактировать")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Toast.makeText(requireContext(), item.title, Toast.LENGTH_SHORT).show()
        when(item.itemId){

            0 ->{

            }
            1 ->{

            }
            2 ->{

            }
        }
        return true
    }

    private fun initListOfDay(){
        val gc: GregorianCalendar =  GregorianCalendar()
        gc.add(Calendar.DATE, firstDay)

        val dayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH)
        var dayOfWeek = gc.get(GregorianCalendar.DAY_OF_WEEK)

        dayOfWeek = (dayOfWeek + 5) % 7
        val strDayOfWeek = dayOfWeekList[dayOfWeek]
        listOfDate = mutableListOf(DateItem(strDayOfWeek, dayOfMonth.toString()))


        for (i in firstDay + 1..lastDay){
            val gc: GregorianCalendar =  GregorianCalendar()
            gc.add(Calendar.DATE, i)

            val dayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH)
            var dayOfWeek = gc.get(GregorianCalendar.DAY_OF_WEEK)

            dayOfWeek = (dayOfWeek + 5) % 7
            val strDayOfWeek = dayOfWeekList[dayOfWeek]


            listOfDate.add(DateItem(strDayOfWeek, dayOfMonth.toString()))
        }
    }




}