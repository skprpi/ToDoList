package com.example.todolist

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Const.Companion.AL_RQS
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors


class EditTaskFragment:  Fragment() {

    lateinit var repository: ItemRepository

    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent

    var task: Task? = null
    var isEditMode = false

    var timeNotificaionButton:List<Button>? = null

    var numButtonNotification: Int = -1

    var typeNotificationButton:List<Button>? = null

    var numTypeNotification: Int = -1

    var selectedDays:List<Button>? = null
    var selectedDaysBool = mutableListOf( false, false, false, false ,false, false ,false)

    var timeInSeconds = -1


    private var buttonNormalBg: Int = Color.parseColor("#FF0000")
    private var buttonActiveBg: Int = Color.parseColor("#FF8BC34A")


    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        repository = ItemRepository.newInstance(requireContext())

        task = arguments?.getParcelable<Task>(ARG_TASK)
        isEditMode = task != null// Mode

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        buttonNormalBg = R.drawable.gray_rounded_bg
        buttonActiveBg = R.drawable.orange_rounded_bg

        alarmManager = activity!!.getSystemService(ALARM_SERVICE) as AlarmManager

        view.findViewById<Button>(R.id.back_options).setOnClickListener{
            (activity as Navigatable).goBack()
        }


        val timeText = view.findViewById<TextView>(R.id.time_text)
        view.findViewById<Button>(R.id.time_picker).setOnClickListener{
            val calender = Calendar.getInstance()
            val timeListener = TimePickerDialog.OnTimeSetListener{ view: TimePicker?, hourOfDay: Int, minute: Int ->

                calender.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calender.set(Calendar.MINUTE, minute)

                timeText.text = SimpleDateFormat("HH:mm").format(calender.time)

                textToSecond(timeText.text.toString())
            }
            TimePickerDialog(requireContext(), timeListener, calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), true).show()
        }

        initTimeNotification(view)
        initSelectedDays(view)
        initTypeNotification(view)

        task?.let{
            view.findViewById<TextInputEditText>(R.id.name_task).setText(it.titleText)
            view.findViewById<TextInputEditText>(R.id.name2_task).setText(it.subtitleText)
            numButtonNotification = it.notification
            selectedDaysBool = it.selectedDays
            numTypeNotification = it.notificationType

            timeInSeconds = it.timeStart

            updateNotification(view)
            updateSelectedDays(view)
            updateTypeNotification(view)
            secondToText(view)
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

    fun textToSecond(text: String){
        if ((text[0] - '0' >= 0) and (text[0] - '0' <= 9)) {
            timeInSeconds =
                ((text[0] - '0') * 10 + (text[1] - '0')) * 3600 + ((text[3] - '0') * 10 + (text[4] - '0')) * 60
            Toast.makeText(requireContext(), "${timeInSeconds}", Toast.LENGTH_SHORT).show()
        }
    }

    fun secondToText(view: View){
        val timeText = view.findViewById<TextView>(R.id.time_text)

        if (timeInSeconds != -1) {
            val calender = Calendar.getInstance()

            val hourOfDay =timeInSeconds / 3600
            val minute = (timeInSeconds - hourOfDay * 3600) / 60

            calender.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calender.set(Calendar.MINUTE, minute)

            timeText.text = SimpleDateFormat("HH:mm").format(calender.time)
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

fun createNotification(){
    var notificationManager = NotificationManagerCompat.from(requireContext())
    val notif =  NotificationCompat.Builder(requireContext(), "1")
        .setContentText(task?.subtitleText)
        .setContentTitle(task?.titleText)
       // .setContentIntent(pendingIntent)
        .setSmallIcon(R.drawable.ic_delete)



    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "Your_channel_id"
        val channel = NotificationChannel(
            channelId,
            "Channel human readable title",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
        notif.setChannelId(channelId)
    }



    notificationManager.notify(null,1, notif.build())
}

    fun saveAll(view: View){
        val buttonSave = view.findViewById<View>(R.id.save_options).setOnClickListener{
            if ((numButtonNotification == -1) || (numTypeNotification == -1)
                || (selectedDaysBool ==
                        mutableListOf( false, false, false, false ,false, false ,false)) ||
                view.findViewById<TextInputEditText>(R.id.name_task).text.toString() == ""){
                Toast.makeText(requireContext(), "Нужно заполнить все поля (примечание можно не указывать)", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
 
            if (isEditMode) {
                task = task?.copy(
                    titleText = view.findViewById<TextInputEditText>(R.id.name_task).text.toString(),
                    subtitleText = view.findViewById<TextInputEditText>(R.id.name2_task).text.toString(),
                    notification = numButtonNotification,
                    notificationType = numTypeNotification,
                    selectedDays = selectedDaysBool,
                    timeStart = timeInSeconds
                )
                Executors.newSingleThreadExecutor().execute{
                    ItemRepository.newInstance(requireContext()).updateTask(task!!)
                }
            }else{
                task = Task(
                    id = 0,
                    titleText = view.findViewById<TextInputEditText>(R.id.name_task).text.toString(),
                    subtitleText = view.findViewById<TextInputEditText>(R.id.name2_task).text.toString(),
                    notification = numButtonNotification,
                    notificationType = numTypeNotification,
                    selectedDays = selectedDaysBool,
                    timeEnd = 0,
                    timeStart = timeInSeconds
                )
                Executors.newSingleThreadExecutor().execute {
                    ItemRepository.newInstance(requireContext()).addItem(task!!)
                }

            }

            createNotification()
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
