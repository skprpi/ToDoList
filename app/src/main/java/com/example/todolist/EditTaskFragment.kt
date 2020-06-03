package com.example.todolist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class EditTaskFragment:  Fragment() {

    lateinit var repository: ItemRepository

    var task: Task? = null

    var timeNotificaionButton:List<View>? = null

    var numButtonNotification: Int = -1

    private var buttonNormalBg: Int = Color.parseColor("#FF0000")
    private var buttonActiveBg: Int = Color.parseColor("#FF8BC34A")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        repository = ItemRepository.instance


        task = arguments?.getParcelable<Task>(ARG_TASK) as Task

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //initTimeNotification(view)

        //saveAll(view)



        initTimeNotification(view)

        task?.let{
            view.findViewById<TextInputEditText>(R.id.name_task).setText(it.titleText)
            view.findViewById<TextInputEditText>(R.id.name2_task).setText(it.subtitleText)
            numButtonNotification = it.notification
            Toast.makeText(requireContext(), numButtonNotification.toString(), Toast.LENGTH_SHORT).show()
            rebootNotification(view)
        }


        saveAll(view)

        view?.findViewById<RecyclerView>(R.id.recyclerView)?.adapter?.notifyDataSetChanged()//My addition ------------------------------------------------------------
        return view
    }

    fun rebootNotification(view: View){
        var counter = 0
        for (item in timeNotificaionButton!!){
            if (numButtonNotification == timeNotificaionButton!!.indexOf(item)){
                item.setBackgroundColor(buttonActiveBg)
            } else{
                item.setBackgroundColor(buttonNormalBg)
            }
            counter += 1
        }
    }

    fun initTimeNotification(view: View){
        timeNotificaionButton = listOf(
            view.findViewById<View>(R.id.option1),
            view.findViewById<View>(R.id.option2),
            view.findViewById<View>(R.id.option3)
        )

        var counter = 0
        for (item in timeNotificaionButton!!){
            item.setBackgroundColor(buttonNormalBg)
            item.setOnClickListener{
                item.setBackgroundColor(buttonActiveBg)
                for (item2 in timeNotificaionButton!!){
                    if (item != item2){
                        item2.setBackgroundColor(buttonNormalBg)
                    }
                }
                numButtonNotification = timeNotificaionButton!!.indexOf(item)
                Toast.makeText(requireContext(), numButtonNotification.toString(), Toast.LENGTH_SHORT).show()
            }
            counter += 1
        }
    }



    fun saveAll(view: View){
        val buttonSave = view.findViewById<View>(R.id.save_options).setOnClickListener{
            task = task?.copy(titleText = view.findViewById<TextInputEditText>(R.id.name_task).text.toString())
            task = task?.copy(subtitleText = view.findViewById<TextInputEditText>(R.id.name2_task).text.toString())
            //task = task?.copy(notification = numButtonNotification)
            task?.notification = numButtonNotification
            Toast.makeText(requireContext(), numButtonNotification.toString(), Toast.LENGTH_SHORT).show()

            task?.let{
                ItemRepository.instance.updateTask(it)
            }
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