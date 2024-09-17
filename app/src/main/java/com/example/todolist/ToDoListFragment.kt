package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.todolist.databinding.ToDoListBinding

class ToDoListFragment : Fragment() {
    private lateinit var binding: ToDoListBinding
    private lateinit var database: ToDoListDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ToDoListBinding.inflate(inflater, container, false)

        database = Room.databaseBuilder(requireActivity(), ToDoListDatabase::class.java, "Note-DB")
            .allowMainThreadQueries().build()
        val notes: List<Note> = database.getNoteDao().getAllData()
        val adapter = DoListAdapter()
        adapter.submitList(notes)
        binding.toDoListRecycler.adapter = adapter


        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_toDoListFragment_to_addNewFragment)
        }


        binding.btnDeleteAll.setOnClickListener {

            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle("Clear Todo List:")
            builder.setMessage("Do you want to clear the todo list?")

            builder.setPositiveButton("Clear List") { p0, p1 ->
                database.getNoteDao().nukeTable()

            }
            builder.setNegativeButton("Cancel") { p0, p1 ->
                p0.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()


        }





        return binding.root
    }
}