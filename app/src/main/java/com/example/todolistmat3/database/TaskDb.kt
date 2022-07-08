package com.example.todolistmat3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDb: RoomDatabase() {
    abstract fun taskDao() : TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDb? = null
        fun getInstance(context: Context): TaskDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDb::class.java,
                        "task_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }}