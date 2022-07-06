package com.example.todolistmat3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
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
//            .setPositiveButton(R.string.alertDialoguePositive) { dialog, _ ->
//                toDoListAdapter.addNewItem(toDoEditText.text.toString())
//                dialog.dismiss()
//            }
            .create()
            .show()
    }}