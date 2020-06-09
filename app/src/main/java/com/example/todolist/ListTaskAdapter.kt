package com.example.todolist

import android.app.ActionBar
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.Executors
import kotlin.coroutines.coroutineContext

class ListTaskAdapter( var listener: ListenerInterface?):RecyclerView.Adapter<ListTaskAdapter.NewItemViewHolder>(){

    val items = mutableListOf<Task>()


    companion object{
        const val TAG = "NewAdapter"
    }

    fun setList(data: MutableList<Task>){
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }


    lateinit var contextMenuListener: View.OnCreateContextMenuListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        val holder = NewItemViewHolder(view)


        view.setOnClickListener{
            val itemPosition = holder.adapterPosition
            listener?.onItemClicked(items[itemPosition])

        }

        //holder.button.setOnCreateContextMenuListener(contextMenuListener)

        holder.button.setOnClickListener{
            val popup: PopupMenu = PopupMenu(parent.context, holder.button)
            // popup.setOnMenuItemClickListener()
            popup.inflate(R.menu.popup_menu)
            popup.setOnMenuItemClickListener {
                when(it.itemId){

                    R.id.item1 ->{

                    }
                    R.id.item2 ->{
                        deleteItem(holder.adapterPosition, holder, parent.context)
                    }
                    R.id.item3 ->{

                    }
                }
                true
            }
            popup.show()
        }

        return holder
    }

    override fun onBindViewHolder(holder: NewItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItem(item: Task){
        items.add(item)
        notifyDataSetChanged()
    }

    fun deleteItem(pos: Int, viewHolder: RecyclerView.ViewHolder, cont: Context){
        if (items.size == 0){
            return
        }
        val removeItem =items[pos]
        ItemRepository.newInstance(cont).removeItem(items[pos])

        Executors.newSingleThreadExecutor().execute{
            val items = ItemRepository.newInstance(cont).getItems()
            setItems(items)
        }
        notifyDataSetChanged()

        /*Snackbar.make(viewHolder.itemView, "$removeItem deleted.", Snackbar.LENGTH_LONG).setAction("UNDO"){
            //items.add(pos, removeItem)
            //notifyItemInserted(pos)

            ItemRepository.newInstance(cont).addItem(removeItem)

            Executors.newSingleThreadExecutor().execute{
                val items = ItemRepository.newInstance(cont).getItems()
                setItems(items)
            }
            notifyDataSetChanged()
        }.show()*/
    }

    fun setItems(tasks: List<Task>, newListener: ListenerInterface? = listener){
        items.clear()
        items.addAll(tasks)

        listener = newListener
        notifyDataSetChanged()//подставим новые данные и отобразим - метод адаптера

    }

    override fun getItemCount() = items.size


    class NewItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title2)

        var dayRepeatTask = mutableListOf<View>(
            itemView.findViewById(R.id.day_1),
            itemView.findViewById(R.id.day_2),
            itemView.findViewById(R.id.day_3),
            itemView.findViewById(R.id.day_4),
            itemView.findViewById(R.id.day_5),
            itemView.findViewById(R.id.day_6),
            itemView.findViewById(R.id.day_7)
        )

        val button = itemView.findViewById<Button>(R.id.option_new_xxx)

        fun bind(item: Task){
            title.text = item.titleText

            var k = item.selectedDays

            for (element in 0..6){
                if (k % 10 == 0){
                    dayRepeatTask[6 - element].visibility = View.GONE
                } else{
                    dayRepeatTask[6 - element].visibility = View.VISIBLE
                }
                k /= 10
            }
        }
    }

   /* public fun showPopup(view: View){
        val popup: PopupMenu = PopupMenu(context, view)
        // popup.setOnMenuItemClickListener()
        popup.inflate(R.menu.popup_menu)
        popup.show()
    }*/

    
}



