package com.example.todolistmat3

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import androidx.room.Database

class ListDataViewModel(var database: Database, application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext

    private val _taskList = MutableLiveData<MutableList<ToDoListItem>>()
    val taskList: LiveData<MutableList<ToDoListItem>>
        get() = _taskList


    fun saveToDoList(toDoListItem: ToDoListItem) {
//        PreferenceManager.getDefaultSharedPreferences(context)
//            .edit()
//            .putStringSet(toDoListItem.description, toDoListItem.taskList.toHashSet())
//            .putInt("position_${toDoListItem.description}", toDoListItem.index)
//            .apply()
//
//        readToDoList()
        database.
    }

    fun readToDoList() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPreferences.all
        var toDoListItems = mutableListOf<ToDoListItem>()

        for (item in contents) {
            if (item.key.startsWith("position")) continue
            val toDoListItem = ArrayList(item.value as HashSet<String>)
            val position = contents["position_${item.key}"] as Int
            toDoListItems.add(ToDoListItem(position, item.key, toDoListItem))
        }

        toDoListItems = toDoListItems.sortedBy { it.index }.toMutableList()
        _taskList.postValue(toDoListItems)
    }
}