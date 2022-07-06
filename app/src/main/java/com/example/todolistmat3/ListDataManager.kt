package com.example.todolistmat3

import android.content.Context
import androidx.preference.PreferenceManager

class ListDataManager(private val context: Context) {

    fun saveToDoList(toDoListItem: ToDoListItem) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putStringSet(toDoListItem.description, toDoListItem.taskList.toHashSet())
            .putInt("position_${toDoListItem.description}", toDoListItem.index)
            .apply()
    }

    fun readToDoList() : MutableList<ToDoListItem> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPreferences.all
        val toDoListItems = mutableListOf<ToDoListItem>()

        for (item in contents) {
            if (item.key.startsWith("position")) continue
            val toDoListItem = ArrayList(item.value as HashSet<String>)
            val position = contents["position_${item.key}"] as Int
            toDoListItems.add(ToDoListItem(position, item.key, toDoListItem))
        }
        return toDoListItems
    }
}