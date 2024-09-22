package com.example.todolist


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.AddNewBinding
import java.time.LocalDateTime

class AddNewFragment : Fragment() {

    private lateinit var binding: AddNewBinding
    private var showTime: String? = null
    private var showDate: String? = null

    private var noteId = 0
    private lateinit var note: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = "Add New"


        binding = AddNewBinding.inflate(inflater, container, false)

        noteId = arguments?.getInt("note", 0) ?: 0



        if (noteId != 0) {
            note = ToDoListDatabase.getDB(requireContext()).getNoteDao()
                .getAllDataWithID(listOf<Int>(noteId))[0]
            binding.apply {
                editEntry.setText(note.title)
                btnDate.text = note.date
                btnTime.text = note.time
            }
        }

        binding.btnDate.setOnClickListener {
            pickDate()
        }
        binding.btnTime.setOnClickListener {
            pickTime()
        }


        binding.btnSubmit.setOnClickListener {
            val titleStr = binding.editEntry.text.toString()
            val timeStr = showTime?:"00:00"
            val dateStr = showDate?:"00:00:0000"
            val entryTimeStr = "Entry time: ${LocalDateTime.now()}"

            if (titleStr.isNotEmpty()) {
                var note = Note(
                    title = titleStr,
                    time = timeStr,
                    date = dateStr,
                    entryTime = entryTimeStr
                )

                if (noteId == 0) {
                    ToDoListDatabase.getDB(requireContext()).getNoteDao().insertData(note)
                } else {
                    note.id = noteId
                    ToDoListDatabase.getDB(requireContext()).getNoteDao().updateData(note)
                }

                findNavController().navigate(R.id.action_addNewFragment_to_toDoListFragment)

            } else {
                Toast.makeText(requireActivity(), "Blank Entry not Allow!", Toast.LENGTH_SHORT)
                    .show()
            }


        }


        return binding.root
    }

    private fun pickDate() {
        val calender = Calendar.getInstance()
        var day = calender.get(Calendar.DAY_OF_MONTH)
        var month = calender.get(Calendar.MONTH)

        var year = calender.get(Calendar.YEAR)


        val datePicker = DatePickerDialog(
            requireActivity(), { _, year, month, day ->
                showDate = "$day/${month + 1}/$year"
                binding.btnDate.text = showDate

            }, year, month, day
        )
        datePicker.show()
    }


    private fun pickTime() {
        val calender = Calendar.getInstance()
        var minute = calender.get(Calendar.MINUTE) //0-59
        var minuteDisplay = minute
        var hour = calender.get(Calendar.HOUR)  //0-11
        var hourDisplay = hour

        val timePicker = TimePickerDialog(
            requireActivity(), { _, hour, minute ->
                hourDisplay = if (hour == 0) {
                    12
                } else {
                    hour
                }
                showTime ="$hour: $minute"
                binding.btnTime.text = showTime

            }, hour, minute, false
        )
        timePicker.show()
    }

}