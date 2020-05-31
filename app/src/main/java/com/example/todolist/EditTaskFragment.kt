package com.example.todolist

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class EditTaskFragment:  Fragment() {

    lateinit var repository: ItemRepository

    var task: Task? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        repository = ItemRepository.instance


        task = arguments?.getParcelable<Task>(ARG_TASK) as Task

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        setTime(view)
        saveAll(view)

        task?.let{
            view.findViewById<TextInputEditText>(R.id.name_task).setText(it.titleText)
            view.findViewById<TextInputEditText>(R.id.name2_task).setText(it.subtitleText)
        }
        saveAll(view)

        view?.findViewById<RecyclerView>(R.id.recyclerView)?.adapter?.notifyDataSetChanged()//My addition ------------------------------------------------------------
        return view
    }

    fun setTime(view: View){
        val button1 = view.findViewById<View>(R.id.option1)
        val button2 = view.findViewById<View>(R.id.option2)
        val button3 = view.findViewById<View>(R.id.option3)

        button1.setOnClickListener{
            button1.setBackgroundColor(Color.RED)
            button2.setBackgroundColor(Color.WHITE)
            button3.setBackgroundColor(Color.WHITE)
        }

        button2.setOnClickListener{
            button2.setBackgroundColor(Color.RED)
            button1.setBackgroundColor(Color.WHITE)
            button3.setBackgroundColor(Color.WHITE)
        }

        button3.setOnClickListener{
            button3.setBackgroundColor(Color.RED)
            button2.setBackgroundColor(Color.WHITE)
            button1.setBackgroundColor(Color.WHITE)
        }
    }

    fun saveAll(view: View){
        val buttonSave = view.findViewById<View>(R.id.save_options).setOnClickListener{
            task = task?.copy(titleText = view.findViewById<TextInputEditText>(R.id.name_task).text.toString())
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