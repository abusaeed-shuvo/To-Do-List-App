package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.todolist.databinding.ListToDoBinding


class DoListAdapter : ListAdapter<Note, ToDoListViewHolder>(comparator) {

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
                toDoListTV.text = it.title
                timeTV.text = it.time
                dateTV.text = it.date
                entryTimeTV.text = it.entryTime
            }

            holder.binding.btnDelete.setOnClickListener {

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