package com.example.todolist

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class ListTaskAdapter(val items: MutableList<Task>, var listener: ListenerInterface?):RecyclerView.Adapter<ListTaskAdapter.NewItemViewHolder>(){

    companion object{
        const val TAG = "NewAdapter"
    }

    lateinit var contextMenuListener: View.OnCreateContextMenuListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        val holder = NewItemViewHolder(view)
        val itemPosition = holder.adapterPosition

        view.setOnClickListener{
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
                        deleteItem(itemPosition + 1, holder)
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

    fun deleteItem(pos: Int, viewHolder: RecyclerView.ViewHolder){
        if (items.size == 0){
            return
        }

        val removeItem =items[pos]

        items.removeAt(pos)
        notifyItemRemoved(pos)

        ItemRepository.instance.removeItem(items[pos])

        Snackbar.make(viewHolder.itemView, "$removeItem deleted.", Snackbar.LENGTH_LONG).setAction("UNDO"){
            items.add(pos, removeItem)
            notifyItemInserted(pos)
        }.show()
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
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)

        val dayRepeatTask = mutableListOf<View>(
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
            subtitle.text = item.subtitleText

            for (element in 0..6){
                if (!item.selectedDays[element]){
                    dayRepeatTask[element].visibility = View.GONE
                } else{
                    dayRepeatTask[element].visibility = View.VISIBLE
                }
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


