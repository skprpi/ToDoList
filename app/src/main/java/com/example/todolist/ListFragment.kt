package com.example.todolist

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class ListFragment: Fragment() {

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: ListTaskAdapter
    private lateinit var adapterDate: DateAdapter

    private lateinit var listOfDate : MutableList<DateItem>

    private lateinit var delIcon : Drawable

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

        clickListenerAdd(view)

        recycler = view.findViewById(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(context)//отвечает за расположение элементов на экране внутри recycler


        val listener = object: ListenerInterface{
            override fun onItemClicked(item: Task) {
                if (activity is Navigatable){
                    (activity as Navigatable).navigateTo(Navigatable.Screens.DETAIL_SCREEN, item)
                }

            }
        }

        adapter = ListTaskAdapter(ItemRepository.instance.getItems()!!, listener)
        adapter.contextMenuListener = this


        recycler.adapter = adapter



        val recyclerDate = view.findViewById<RecyclerView>(R.id.recyclerDate)
        recyclerDate.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)//отвечает за расположение элементов на экране внутри recycler

        adapterDate = DateAdapter()
        initListOfDay()
        adapterDate.setItems(listOfDate)

        recyclerDate.adapter = adapterDate
        recyclerDate.scrollToPosition((listOfDate.size / 2) - 1)


        delIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_delete)!!



        var itemTouchHelper = ItemTouchHelper(SwipeToDelete(adapter, recycler,  delIcon))
        itemTouchHelper.attachToRecyclerView(recycler)

        return view
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


    private fun clickListenerAdd(view: View) {
        view.findViewById<View>(R.id.add_item_button).setOnClickListener() {

            val newItem =  Task(adapter.itemCount,"", "", MutableList<Boolean>(7) {false}, -1,-1,-1,-1)
            adapter.addItem(newItem)

            if (activity is Navigatable){
                (activity as Navigatable).navigateTo(Navigatable.Screens.DETAIL_SCREEN, newItem)
            }
        }

    }






}