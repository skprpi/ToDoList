package com.example.todolist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(var id: Int,
                var titleText:String,
                var subtitleText: String,
                var selectedDays: MutableList<Boolean>,
                var timeStart: Long,
                var timeEnd : Long,
                var notification: Int,
                var notificationType : Int
) : Parcelable