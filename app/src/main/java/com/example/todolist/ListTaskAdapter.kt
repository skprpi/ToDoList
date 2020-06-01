package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListTaskAdapter(val items: MutableList<Task>, var listener: ListenerInterface?):RecyclerView.Adapter<ListTaskAdapter.NewItemViewHolder>() {

    companion object{
        const val TAG = "NewAdapter"
    }

    lateinit var contextMenuListener: View.OnCreateContextMenuListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        val holder = NewItemViewHolder(view)

        view.setOnClickListener{

            //Toast.makeText(parent.context, "All Good", Toast.LENGTH_SHORT).show()

            val itemPosition = holder.adapterPosition
            listener?.onItemClicked(items[itemPosition])
        }

        holder.button.setOnCreateContextMenuListener(contextMenuListener)

        return holder
    }

    override fun onBindViewHolder(holder: NewItemViewHolder, position: Int) {
        holder.bind(items[position])
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
        val image: ImageView = itemView.findViewById(R.id.image2)
        val button = itemView.findViewById<Button>(R.id.option_new_xxx)

        fun bind(item: Task){
            title.text = item.titleText
            subtitle.text = item.subtitleText
        }
    }


    
}


