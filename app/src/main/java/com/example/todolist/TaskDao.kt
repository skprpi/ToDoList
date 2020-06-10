package com.example.todolist

import androidx.room.*

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM Task WHERE selectedDays LIKE '%' || :day || '%'")
    fun getTaskByDay(day: String): List<Task>

    @Query("SELECT count(id) FROM Task WHERE selectedDays LIKE '%' || :day || '%'")
    fun getCountElementInList(day: String): Int

    @Query("SELECT * FROM Task")
    fun getAll() : List<Task>
}