package com.example.todolist

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Task(
                var titleText:String,
                var subtitleText: String,
                var selectedDays: Int,
                var timeStart: Long,
                var timeEnd : Long,
                var notification: Int,
                var notificationType : Int
) : Parcelable{
    @PrimaryKey(autoGenerate = true)
    var id = 0

    companion object{
        fun selectedToInt(list: MutableList<Boolean>): Int{
            var k = 0
            for (i in 0..6){
                val d = if (list[i]) 1  else 0

                k = (k * 10) + d
            }
            return k
        }

        fun selectedToBool(p: Int): MutableList<Boolean>{
            var mList = mutableListOf(false, false, false, false, false, false, false)
            var k = p
            for (element in 0..6){
                mList[6 - element] = k % 10 != 0
                k /= 10
            }
            return mList
        }
    }
}