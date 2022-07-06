package com.example.todolistmat3

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoItemViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {

    val toDoIndexView : TextView = itemView.findViewById(R.id.itemNr)
    val toDoContentView :  TextView = itemView.findViewById(R.id.itemValue)

    fun BindValues(index: Int, value: ToDoListItem) {
        toDoIndexView.text = index.toString()
        value.index = index
        toDoContentView.text = value.description
    }
}