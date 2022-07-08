package com.example.todolistmat3.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId: Long = 0,
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "position")
    var positionInTodo: Int = 0,
    @ColumnInfo(name = "toDoOwner")
    var toDoListName: String = ""
)
