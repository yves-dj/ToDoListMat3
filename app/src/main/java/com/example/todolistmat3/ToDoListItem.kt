package com.example.todolistmat3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ToDoListItem(var index: Int, var description: String, var taskList: MutableList<String>) : Parcelable{
}
