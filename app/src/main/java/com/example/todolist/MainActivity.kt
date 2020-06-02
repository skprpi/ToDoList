package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.todolist.ItemRepository.Companion.instance


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







    /* fun clickListenerAddandRemove(){
         findViewById<View>(R.id.addButton).setOnClickListener(){

             newItem = NewItem("", "", Color.CYAN)

             if (items.size > 0)
                 items.add(items.indexOf(items.last()), newItem)
             else
                 items.add(0, newItem)

             recycler.adapter?.notifyDataSetChanged()


             val activityList: ActivityList = ActivityList()

             val intent = Intent(this, activityList::class.java)
             startActivity(intent)

         }

         findViewById<View>(R.id.removeButton).setOnClickListener(){
             if (items.size > 0) {
                 items.removeAt(items.lastIndexOf(items.last()))
                 recycler.adapter?.notifyDataSetChanged()
             }
         }
     }*/

}



