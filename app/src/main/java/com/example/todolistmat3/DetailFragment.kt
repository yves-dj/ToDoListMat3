package com.example.todolistmat3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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


    private lateinit var detailedToDo: ToDoListItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailedToDo = args.toDoListItem


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
}