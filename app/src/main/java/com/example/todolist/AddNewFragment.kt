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
import androidx.room.Room
import com.example.todolist.databinding.AddNewBinding
import java.time.LocalDateTime

class AddNewFragment : Fragment() {

    private lateinit var binding: AddNewBinding
    private lateinit var database: ToDoListDatabase
    private var showTime: String? = null
    private var showDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = "Add New"

        binding = AddNewBinding.inflate(inflater, container, false)

        database = Room.databaseBuilder(requireActivity(), ToDoListDatabase::class.java, "Note-DB")
            .allowMainThreadQueries().build()



        binding.btnDate.setOnClickListener {
            pickDate()
        }
        binding.btnTime.setOnClickListener {
            pickTime()
        }


        binding.btnSubmit.setOnClickListener {
            val titleStr = binding.editEntry.text.toString()
            val timeStr = showTime ?: "00:00"
            val dateStr = showDate ?: "00/00/0000"
            val entryTimeStr = "Entry time: ${LocalDateTime.now()}"

            if (titleStr.isNotEmpty()) {
                val note = Note(
                    title = titleStr,
                    time = timeStr,
                    date = dateStr,
                    entryTime = entryTimeStr
                )
                database.getNoteDao().insertData(note)

                Toast.makeText(requireActivity(), "Entry Allowed!", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(requireActivity(), "Blank Entry not Allow!", Toast.LENGTH_SHORT)
                    .show()
            }


        }
        binding.btnReturn.setOnClickListener {
            findNavController().navigate(R.id.action_addNewFragment_to_toDoListFragment)
        }

        return binding.root
    }

    private fun pickDate() {
        val calender = Calendar.getInstance()
        var day = calender.get(Calendar.DAY_OF_MONTH)
        var month = calender.get(Calendar.MONTH)
        var monthDisplay = month + 1
        var year = calender.get(Calendar.YEAR)


        val datePicker = DatePickerDialog(
            requireActivity(), { _, year, month, day ->
                showDate = "$day/$monthDisplay/$year"
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
                showTime = "$hourDisplay : $minute"
                binding.btnTime.text = showTime

            }, hour, minute, false
        )
        timePicker.show()
    }

}