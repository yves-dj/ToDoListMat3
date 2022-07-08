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
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolistmat3.databinding.FragmentTasklistBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentTasklistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var viewModel: ListDataViewModel


    private lateinit var detailedToDo: ToDoListItem

    private var detailedThingsToDo = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailedToDo = args.toDoListItem
//        viewModel = ViewModelProvider(this).get(ListDataViewModel::class.java)
//        viewModel.taskList.observe(this) {
//            toDoListAdapter.inputList = ArrayList(it)
//            toDoListAdapter.notifyDataSetChanged()
//        }


        _binding = FragmentTasklistBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = detailedToDo.description


//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_TaskListFragment_to_ToDoListFragment3)
//        }
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
            .setTitle("Edit Detailed List")
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
}