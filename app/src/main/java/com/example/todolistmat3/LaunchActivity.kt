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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        var thingsToDo = listOf<String>("Kuisen", "Koken", "Vetzakkerij", "Niks doen")
        recyclerView = findViewById(R.id.toDoListView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            toDoListAdapter = ToDoListAdapter(thingsToDo)
            adapter = toDoListAdapter
        }

        val toDoEditText = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_FLAG_CAP_WORDS
        }

        fab = findViewById(R.id.fabAddToList)
        fab.setOnClickListener {
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
}