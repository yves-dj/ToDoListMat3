package com.example.todolistmat3

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ToDoListAdapter(val inputList: List<String>) : RecyclerView.Adapter<ToDoItemViewHolder>() {
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

    fun addNewItem(toString: String) {

    }

}