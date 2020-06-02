package com.example.todolist

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class SwipeToDelete(var adapter: ListTaskAdapter, recycler: RecyclerView) : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        var pos = viewHolder.adapterPosition
        when(direction){
            ItemTouchHelper.LEFT->{
                adapter.deleteItem(pos, viewHolder)
            }
            ItemTouchHelper.RIGHT->{

            }
        }

    }

}