package com.example.todolistmat3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(private var inputList: MutableList<ToDoListItem>) : RecyclerView.Adapter<ToDoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.recycler_todoitemview , parent, false)
        return ToDoItemViewHolder(item)
    }

    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        holder.BindValues(position + 1, inputList[position])
    }

    override fun getItemCount(): Int {
        return inputList.size
    }

    fun addNewItem(itemDescrToAdd: String) {
        inputList.add(
            if (itemDescrToAdd.isEmpty())
                ToDoListItem(inputList.size + 1, "New item ${inputList.size + 1}", mutableListOf())
            else
                ToDoListItem(inputList.size + 1, itemDescrToAdd, mutableListOf())
        )
        notifyItemInserted(inputList.size - 1)
    }

    fun resetToDoList(newToDoList: MutableList<ToDoListItem>) {
        inputList = newToDoList
    }
}

