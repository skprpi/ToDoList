package com.example.todolist
import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.*
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        Db.getInstance(this)
    }

    object Db {
        private var INSTANCE: AppDb? = null

        fun getInstance(context: Context): AppDb? {
            if (INSTANCE == null) {
                synchronized(AppDb::class) {

                    INSTANCE = databaseBuilder(
                        context,
                        AppDb::class.java, "db-name.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE?.close()
            INSTANCE = null
        }
    }

    @Database(entities = arrayOf(Task::class), version = 2)
    @TypeConverters(SelectedDayConverter::class)
    abstract class AppDb : RoomDatabase() {
        abstract fun getTaskDao(): TaskDao

    }
}