package com.example.todolistmat3

import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistmat3.databinding.FragmentTodolistBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ToDoListFragment : Fragment() {

    private var _binding: FragmentTodolistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var thingsToDo = mutableListOf<ToDoListItem>()

    private lateinit var toDoListAdapter: ToDoListAdapter

    private lateinit var dataMngr : ListDataManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var firstThing = ToDoListItem(0, "Kuisen", mutableListOf())
        var secondThing = ToDoListItem(0, "Koken", mutableListOf())
        thingsToDo.add(firstThing)
        thingsToDo.add(secondThing)

        _binding = FragmentTodolistBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataMngr = ListDataManager(view.context)

        with(binding.fragmentRecycle) {
            layoutManager = LinearLayoutManager(context)
            toDoListAdapter = ToDoListAdapter(thingsToDo)
            adapter = toDoListAdapter
        }


        binding.fragmentFab.setOnClickListener{
            createDialogue(view.context)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createDialogue(context: Context) {
        val toDoEditText = EditText(context).apply {
            inputType = InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_FLAG_CAP_WORDS
        }

        AlertDialog.Builder(context)
            .setTitle("Edit List")
            .setMessage("")
            .setView(toDoEditText) // Adds specified view to alertDialogue
            .setPositiveButton(R.string.alertDialoguePositive) { dialog, _ ->
                toDoListAdapter.addNewItem(toDoEditText.text.toString())
                dialog.dismiss()
            }
            .create()
            .show()
    }}