package com.example.todolistmat3

import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

    private var hasLoadedList = false

//    private lateinit var dataMngr : ListDataManager

    private lateinit var viewModel: ListDataViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListDataViewModel::class.java)
        viewModel.taskList.observe(this) {
            toDoListAdapter.inputList = ArrayList(it)
            toDoListAdapter.notifyDataSetChanged()
        }

//        if (!hasLoadedList) {
//            binding.fragmentRecycle.adapter = toDoListAdapter
//            hasLoadedList = true
//        }
//
//        viewModel.readToDoList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Doesnt work
        var firstThing = ToDoListItem(0, "Kuisen", mutableListOf())
        var secondThing = ToDoListItem(1, "Koken", mutableListOf())
        thingsToDo.add(firstThing)
        thingsToDo.add(secondThing)

        _binding = FragmentTodolistBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        dataMngr = ListDataManager(view.context)

        with(binding.fragmentRecycle) {
            layoutManager = LinearLayoutManager(context)
            toDoListAdapter = ToDoListAdapter(thingsToDo) { selectedToDoList ->
                navigateToTaskList(selectedToDoList, view)
            }
            adapter = toDoListAdapter
        }


        binding.fragmentFab.setOnClickListener{
            createDialogue(view.context)
//            navigateToTaskList(thingsToDo[0])
        }
        viewModel.readToDoList()
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
                viewModel.saveToDoList(ToDoListItem(thingsToDo.size - 1, toDoEditText.text.toString(), mutableListOf()))
//                toDoListAdapter.addNewItem(toDoEditText.text.toString())
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun navigateToTaskList(listItem: ToDoListItem, view: View) {
        Toast.makeText(view.context, "listItem ${listItem.index} clicked", Toast.LENGTH_LONG).show()
        // Does not work
        if (listItem.taskList.size == 0) {
            val chars = ('a'..'z')
            for (i in 0..4) {
                listItem.taskList.add(List(9) { chars.random()}.joinToString { "" })
            }
            toDoListAdapter.notifyDataSetChanged()
        }
        val action = ToDoListFragmentDirections.actionToDoListFragmentToTaskListFragment3(listItem)
        findNavController().navigate(action)
    }
}