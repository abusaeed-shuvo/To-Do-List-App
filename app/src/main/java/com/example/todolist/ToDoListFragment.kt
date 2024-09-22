package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.ToDoListBinding

class ToDoListFragment : Fragment(), DoListAdapter.NoteEdit {
    private lateinit var binding: ToDoListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activity?.title = "To Do List"
        binding = ToDoListBinding.inflate(inflater, container, false)
        val notes = ToDoListDatabase.getDB(requireContext()).getNoteDao().getAllData()



        if (notes.isNotEmpty()) {
            val adapter = DoListAdapter(this)
            adapter.submitList(notes)
            binding.toDoListRecycler.adapter = adapter

            binding.toDoListRecyclerPlaceHolder.visibility = View.GONE
            binding.toDoListRecycler.visibility = View.VISIBLE
        } else {
            binding.toDoListRecyclerPlaceHolder.visibility = View.VISIBLE
            binding.toDoListRecycler.visibility = View.GONE
        }






        binding.btnAdd.setOnClickListener {

            findNavController().navigate(R.id.action_toDoListFragment_to_addNewFragment)
        }



        binding.btnDeleteAll.setOnClickListener {

            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle("Clear Todo List:")
            builder.setMessage("Do you want to clear the todo list?")

            builder.setPositiveButton("Clear List") { p0, p1 ->
                ToDoListDatabase.getDB(requireContext()).getNoteDao().nukeTable()
                findNavController().navigate(R.id.action_toDoListFragment_to_returnHomeFragment)

            }
            builder.setNegativeButton("Cancel") { p0, p1 ->
                p0.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
        return binding.root
    }

    override fun onNoteEdit(note: Note) {

        var bundle = Bundle().apply {

            putInt("note", note.id)
        }
        findNavController().navigate(R.id.action_toDoListFragment_to_addNewFragment, bundle)

    }

    override fun deleteNote(note: Note) {
        ToDoListDatabase.getDB(requireContext()).getNoteDao().deleteData(note)

        findNavController().navigate(R.id.action_toDoListFragment_to_returnHomeFragment)
    }


}