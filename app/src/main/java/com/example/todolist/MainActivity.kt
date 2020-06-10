package com.example.todolist

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment



class MainActivity : AppCompatActivity(), Navigatable {

    lateinit var task:Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       navigateTo(Navigatable.Screens.ITEM_LIST)
    }

    override fun navigateTo(screen: Navigatable.Screens, data: Any?) {
        val ft = supportFragmentManager.beginTransaction()
        val fragment:Fragment

        when(screen){
            Navigatable.Screens.ITEM_LIST -> fragment = ListFragment()
            Navigatable.Screens.DETAIL_SCREEN -> {
                fragment = if (data is Task){
                    EditTaskFragment.newInstance(data)
                } else {
                    EditTaskFragment()
                }
            }
        }

        if (supportFragmentManager.backStackEntryCount == 0){
            ft.add(R.id.root_container, fragment, screen.name)
        } else{
            ft.replace(R.id.root_container, fragment, screen.name)
        }

        ft.addToBackStack(screen.name)
            .commit()
    }

    override fun onBackPressed() {
        goBack()
    }

    override fun goBack() {
        if (supportFragmentManager.backStackEntryCount > 1)
            supportFragmentManager.popBackStack()

    }

}



