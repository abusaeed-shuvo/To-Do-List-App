package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.room.Room
import com.example.todolist.databinding.ListToDoBinding


class DoListAdapter : ListAdapter<Note, ToDoListViewHolder>(comparator) {


    private lateinit var database: ToDoListDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {

        return ToDoListViewHolder(
            ListToDoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {


        getItem(position).let {
            holder.binding.apply {
                val note = Note(it.id, it.title, it.time, it.date, it.entryTime)
                toDoListTV.text = it.title
                timeTV.text = it.time
                dateTV.text = it.date
                entryTimeTV.text = it.entryTime


                btnDelete.setOnClickListener {
//                    Toast.makeText(it.context, "$note", Toast.LENGTH_SHORT).show()
                    database =
                        Room.databaseBuilder(it.context, ToDoListDatabase::class.java, "Note-DB")
                            .allowMainThreadQueries().build()

                    val builder = AlertDialog.Builder(it.context)
                    builder.setTitle("Clear This Entry:")
                    builder.setMessage("Do you want to clear the ${note.title}?")

                    builder.setPositiveButton("Clear List") { p0, p1 ->

                        database.getNoteDao().deleteData(note)

                        it.findNavController()
                            .navigate(R.id.action_toDoListFragment_to_returnHomeFragment)


                    }
                    builder.setNegativeButton("Cancel") { p0, p1 ->
                        p0.cancel()
                    }

                    val alertDialog = builder.create()
                    alertDialog.show()


                }
            }


        }


    }


    companion object {
        val comparator = object : DiffUtil.ItemCallback<Note>() {

            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

        }
    }


}