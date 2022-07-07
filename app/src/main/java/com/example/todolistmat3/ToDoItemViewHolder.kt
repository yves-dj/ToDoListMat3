package com.example.todolistmat3

import android.content.ClipData
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoItemViewHolder(var itemView: View, var onClickListener: ToDoListAdapter.ToDoListOnClickListener) : RecyclerView.ViewHolder(itemView) {

    val toDoIndexView : TextView = itemView.findViewById(R.id.itemNr)
    val toDoContentView :  TextView = itemView.findViewById(R.id.itemValue)

    fun BindValues(index: Int, value: ToDoListItem) {
        toDoIndexView.text = index.toString()
        value.index = index
        toDoContentView.text = value.description
        itemView.setOnClickListener { onClickListener.addOnClickListener(value) }
    }
}