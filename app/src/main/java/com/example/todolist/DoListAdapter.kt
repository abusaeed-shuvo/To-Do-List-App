package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
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
                    database = Room.databaseBuilder(it.context, ToDoListDatabase::class.java, "Note-DB")
                        .allowMainThreadQueries().build()


                    database.getNoteDao().deleteData(note)

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