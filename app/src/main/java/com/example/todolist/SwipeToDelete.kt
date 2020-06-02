package com.example.todolist

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.coroutines.coroutineContext

class SwipeToDelete(var adapter: ListTaskAdapter,var  recycler: RecyclerView, val delIcon: Drawable) : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

    private var swipeBackgrownd: ColorDrawable = ColorDrawable(Color.parseColor("#FF0000"))


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

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {



        val itemView2 = viewHolder.itemView

        val iconMargin = itemView2.height - delIcon.intrinsicHeight / 2

        val itemView = viewHolder.itemView
        if (dX > 0){
            swipeBackgrownd.setBounds(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
            delIcon.setBounds(itemView.left + iconMargin, itemView.top + iconMargin,
                itemView.left + iconMargin + delIcon.intrinsicWidth, itemView.bottom - iconMargin)
        }else{
            swipeBackgrownd.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
            delIcon.setBounds(itemView.right - iconMargin - delIcon.intrinsicWidth, itemView.top + iconMargin,
                itemView.right - iconMargin , itemView.bottom - iconMargin)
        }
        swipeBackgrownd.draw(c)

        c.save()

        if (dX > 0){
            c.clipRect(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
        }else{
            c.clipRect(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
        }
        delIcon.draw(c)

        c.restore()

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}