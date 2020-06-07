package com.example.todolist

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class EditTaskFragment:  Fragment() {

    lateinit var repository: ItemRepository

    var task: Task? = null
    var isEditMode = false

    var timeNotificaionButton:List<Button>? = null

    var numButtonNotification: Int = -1

    var typeNotificationButton:List<Button>? = null

    var numTypeNotification: Int = -1

    var selectedDays:List<Button>? = null
    var selectedDaysBool = mutableListOf( false, false, false, false ,false, false ,false)

    private var buttonNormalBg: Int = Color.parseColor("#FF0000")
    private var buttonActiveBg: Int = Color.parseColor("#FF8BC34A")


    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        repository = ItemRepository.newInstance(requireContext())

        isEditMode = task != null// Mode

        task = arguments?.getParcelable<Task>(ARG_TASK)

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        buttonNormalBg = R.drawable.gray_rounded_bg
        buttonActiveBg = R.drawable.orange_rounded_bg


        initTimeNotification(view)
        initSelectedDays(view)
        initTypeNotification(view)

        task?.let{
            view.findViewById<TextInputEditText>(R.id.name_task).setText(it.titleText)
            view.findViewById<TextInputEditText>(R.id.name2_task).setText(it.subtitleText)
            numButtonNotification = it.notification
            selectedDaysBool = Task.selectedToBool(it.selectedDays)
            numTypeNotification = it.notificationType
            updateNotification(view)
            updateSelectedDays(view)
            updateTypeNotification(view)
        }


        saveAll(view)

        view?.findViewById<RecyclerView>(R.id.recyclerView)?.adapter?.notifyDataSetChanged()//My addition ------------------------------------------------------------
        return view
    }

    @SuppressLint("ResourceType")
    fun updateNotification(view: View){
        for (item in timeNotificaionButton!!){
            if (numButtonNotification == timeNotificaionButton!!.indexOf(item)){
                item.setBackgroundResource(buttonActiveBg)
                item.setTextColor(Color.WHITE)
            } else{
                item.setBackgroundResource(buttonNormalBg)
                item.setTextColor(Color.BLACK)
            }
        }
    }

    @SuppressLint("ResourceType")
    fun updateTypeNotification(view: View){
        for (item in typeNotificationButton!!){
            if (numTypeNotification == typeNotificationButton!!.indexOf(item)){
                item.setBackgroundResource(buttonActiveBg)
                item.setTextColor(Color.WHITE)
            } else{
                item.setBackgroundResource(buttonNormalBg)
                item.setTextColor(Color.BLACK)
            }
        }
    }

    @SuppressLint("ResourceType")
    fun initSelectedDays(view: View){
        selectedDays = listOf(
            view.findViewById<Button>(R.id.day1),
            view.findViewById<Button>(R.id.day2),
            view.findViewById<Button>(R.id.day3),
            view.findViewById<Button>(R.id.day4),
            view.findViewById<Button>(R.id.day5),
            view.findViewById<Button>(R.id.day6),
            view.findViewById<Button>(R.id.day7)
        )

        var counter = 0
        for (item in selectedDays!!){
            item.setTextColor(Color.BLACK)
            item.setBackgroundResource(buttonNormalBg)
            item.setOnClickListener{
                if (selectedDaysBool.get(selectedDays!!.indexOf(item))){   /*selectedDays!!.indexOf(item)*/

                    selectedDaysBool.set(selectedDays!!.indexOf(item), false)
                    item.setTextColor(Color.BLACK)
                    item.setBackgroundResource(buttonNormalBg)
                } else{
                    selectedDaysBool.set(selectedDays!!.indexOf(item), true)
                    item.setTextColor(Color.WHITE)
                    item.setBackgroundResource(buttonActiveBg)
                }
            }
            counter += 1
        }
    }

    @SuppressLint("ResourceType")
    fun updateSelectedDays(view: View){
        for (item in selectedDays!!) {
            if (selectedDaysBool[selectedDays!!.indexOf(item)]){
                item.setTextColor(Color.WHITE)
                item.setBackgroundResource(buttonActiveBg)
            } else{
                item.setTextColor(Color.BLACK)
                item.setBackgroundResource(buttonNormalBg)
            }
        }
    }


    @SuppressLint("ResourceType")
    fun initTypeNotification(view: View){
        typeNotificationButton = arrayListOf(
            view.findViewById<Button>(R.id.typeNotification1),
            view.findViewById<Button>(R.id.typeNotification2),
            view.findViewById<Button>(R.id.typeNotification3)
        )

        var counter = 0
        for (item in typeNotificationButton!!){

            item.setBackgroundResource(buttonNormalBg)
            item.setTextColor(Color.BLACK)

            item.setOnClickListener{
                item.setTextColor(Color.WHITE)
                item.setBackgroundResource(buttonActiveBg)

                for (item2 in typeNotificationButton!!){
                    if (item != item2){
                        item2.setTextColor(Color.BLACK)
                        item2.setBackgroundResource(buttonNormalBg)
                    }
                }
                numTypeNotification = typeNotificationButton!!.indexOf(item)
            }
            counter += 1
        }
    }


    @SuppressLint("ResourceType")
    fun initTimeNotification(view: View){
        timeNotificaionButton = arrayListOf(
            view.findViewById<Button>(R.id.option1),
            view.findViewById<Button>(R.id.option2),
            view.findViewById<Button>(R.id.option3),
            view.findViewById<Button>(R.id.option4),
            view.findViewById<Button>(R.id.option5),
            view.findViewById<Button>(R.id.option6)
        )

        var counter = 0
        for (item in timeNotificaionButton!!){

            item.setBackgroundResource(buttonNormalBg)
            item.setTextColor(Color.BLACK)

            item.setOnClickListener{
                item.setTextColor(Color.WHITE)
                item.setBackgroundResource(buttonActiveBg)

                for (item2 in timeNotificaionButton!!){
                    if (item != item2){
                        item2.setTextColor(Color.BLACK)
                        item2.setBackgroundResource(buttonNormalBg)
                    }
                }
                numButtonNotification = timeNotificaionButton!!.indexOf(item)
            }
            counter += 1
        }
    }



    fun saveAll(view: View){

        val buttonSave = view.findViewById<View>(R.id.save_options).setOnClickListener{
            if (isEditMode) {
                task = task?.copy(
                    titleText = view.findViewById<TextInputEditText>(R.id.name_task).text.toString(),
                    subtitleText = view.findViewById<TextInputEditText>(R.id.name2_task).text.toString(),
                    notification = numButtonNotification,
                    notificationType = numTypeNotification,
                    selectedDays = Task.selectedToInt(selectedDaysBool)
                )
                ItemRepository.newInstance(requireContext()).updateTask(task!!)
            }else{
                task = Task(
                    id = 0,
                    titleText = view.findViewById<TextInputEditText>(R.id.name_task).text.toString(),
                    subtitleText = view.findViewById<TextInputEditText>(R.id.name2_task).text.toString(),
                    notification = numButtonNotification,
                    notificationType = numTypeNotification,
                    selectedDays = Task.selectedToInt(selectedDaysBool),
                    timeEnd = 0,
                    timeStart = 0
                )
                ItemRepository.newInstance(requireContext()).addItem(task!!)

            }
            (activity as Navigatable).goBack()
        }
    }


    companion object {

        const val ARG_TASK = "ARG_TASK"

        fun newInstance(task: Task) = EditTaskFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_TASK, task)
            }
        }
    }
}