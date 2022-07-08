package com.example.todolistmat3.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM Task WHERE toDoOwner =:toDoList")
    fun selectAll(toDoList: String): LiveData<MutableList<Task>>

    @Query("SELECT toDoOwner FROM Task GROUP BY toDoOwner")
    suspend fun getToDoList()
}