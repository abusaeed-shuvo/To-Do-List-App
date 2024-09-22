package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.todolist.databinding.ListToDoBinding


class DoListAdapter(var noteEdit: NoteEdit) : ListAdapter<Note, ToDoListViewHolder>(comparator) {


    interface NoteEdit {

        fun onNoteEdit(note: Note)

        fun deleteNote(note: Note)
    }

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
                if (it.time != "00:00") {
                    timeTV.text = it.time
                } else {
                    timeTV.visibility = View.GONE
                }
                if (it.date != "00:00:0000") {
                    dateTV.text = it.date
                } else {
                    dateTV.visibility = View.GONE
                }

                entryTimeTV.text = it.entryTime

                btnEdit.setOnClickListener { _ ->
                    noteEdit.onNoteEdit(note)
                }


                btnDelete.setOnClickListener {
                    val builder = AlertDialog.Builder(it.context)
                    builder.setTitle("Clear This Entry:")
                    builder.setMessage("Do you want to delete this entry?")

                    builder.setPositiveButton("Delete") { p0, p1 ->

                        noteEdit.deleteNote(note)

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