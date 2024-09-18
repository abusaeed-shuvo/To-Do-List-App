package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.FragmentReturnHomeBinding

class ReturnHomeFragment : Fragment() {
    private lateinit var binding: FragmentReturnHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReturnHomeBinding.inflate(inflater, container, false)
        activity?.title = "Completed!"
        binding.btnReturnHome.setOnClickListener {
            findNavController().navigate(R.id.action_returnHomeFragment_to_toDoListFragment)
        }

        return binding.root
    }


}