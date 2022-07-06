package com.example.todolistmat3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LaunchActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var toDoListAdapter: ToDoListAdapter

    private lateinit var fab : FloatingActionButton

//    private var thingsToDo = mutableListOf<String>()
    private var thingsToDo = mutableListOf<ToDoListItem>()

    private lateinit var dataMngr : ListDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

//        thingsToDo = mutableListOf<String>("Kuisen", "Koken", "Vetzakkerij", "Niks doen")
        var firstThing = ToDoListItem(0, "Kuisen", mutableListOf())
        var secondThing = ToDoListItem(0, "Koken", mutableListOf())
        thingsToDo.add(firstThing)
        thingsToDo.add(secondThing)


        recyclerView = findViewById(R.id.toDoListView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            toDoListAdapter = ToDoListAdapter(thingsToDo)
            adapter = toDoListAdapter
        }

        dataMngr = ListDataManager(this)

        fab = findViewById(R.id.fabAddToList)
        fab.setOnClickListener {
            createDialogue()
        }
    }

    override fun onStart() {
        // TODO:  
        super.onStart()
        if (dataMngr.readToDoList().size != 0) {
            thingsToDo = dataMngr.readToDoList()
            toDoListAdapter.resetToDoList(thingsToDo)
        } else {
            toDoListAdapter.resetToDoList(thingsToDo)
        }
    }

    override fun onStop() {
        super.onStop()
        for (thingToDo in thingsToDo)
            dataMngr.saveToDoList(thingToDo)
    }

    private fun createDialogue() {
        val toDoEditText = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_FLAG_CAP_WORDS
        }

        AlertDialog.Builder(this)
            .setTitle("Edit List")
            .setMessage("")
            .setView(toDoEditText) // Adds specified view to alertDialogue
            .setPositiveButton(R.string.alertDialoguePositive) { dialog, _ ->
                toDoListAdapter.addNewItem(toDoEditText.text.toString())
                dialog.dismiss()
            }
            .create()
            .show()
    }

}