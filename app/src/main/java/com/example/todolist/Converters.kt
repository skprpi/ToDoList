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

        val list = mutableListOf(false, false, false, false, false, false, false)
        if (str.isEmpty()){
            return list
        }
        val split = str.split(",")
        for (el in split){
            if (el.isEmpty()){
                continue
            }
            list[dayOfWeekList.indexOf(el)] = true
        }
        return list
    }
}