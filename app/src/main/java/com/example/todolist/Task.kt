package com.example.todolist

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Task( @PrimaryKey(autoGenerate = true) var id: Int,
                var titleText:String,
                var subtitleText: String,
                 @TypeConverters(SelectedDayConverter::class)
                var selectedDays: MutableList<Boolean>,
                var timeStart: Int,
                var timeEnd : Long,
                var notification: Int,
                var notificationType : Int
) : Parcelable{//обект к строке
}