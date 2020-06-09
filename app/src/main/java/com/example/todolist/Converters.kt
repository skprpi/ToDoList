package com.example.todolist

import androidx.room.TypeConverter

class SelectedDayConverter(){
    private val dayOfWeekList =listOf(
        "ПН",
        "ВТ",
        "СР",
        "ЧТ",
        "ПТ",
        "СБ",
        "ВС"
    )

    @TypeConverter
    fun fromBoolList(list: MutableList<Boolean>): String{
        var str = ""
        for(el in 0..6){
            if (list[el]){
                str += "${dayOfWeekList[el]},"
            }
        }
        return str
    }

    @TypeConverter
    fun toBoolList(str: String): MutableList<Boolean>{
        val split = str.split(",")
        val list = mutableListOf(false, false, false, false, false, false, false)
        for (el in split){
            list[dayOfWeekList.indexOf(el)] = true
        }
        return list
    }
}