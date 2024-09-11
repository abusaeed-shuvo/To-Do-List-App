package com.example.todolist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.AddNewBinding

class AddNewFragment : Fragment() {

    private lateinit var binding: AddNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newList= mutableListOf<String>()

        binding=AddNewBinding.inflate(inflater,container,false)

        binding.btnSubmit.setOnClickListener {
        val entry=binding.editEntry.text.toString()
        newList.add(entry)
            Toast.makeText(activity, "$entry added!", Toast.LENGTH_SHORT).show()
        }
        binding.btnReturn.setOnClickListener {
            findNavController().navigate(R.id.action_addNewFragment_to_toDoListFragment)
        }

        return binding.root
    }


}