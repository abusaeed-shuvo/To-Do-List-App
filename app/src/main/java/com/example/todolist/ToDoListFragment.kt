package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.ToDoListBinding
import java.time.LocalDateTime

class ToDoListFragment : Fragment() {
    private lateinit var binding: ToDoListBinding
    private lateinit var la: ListAdapter
    private val toDoList = mutableListOf<String>(
        "To Do List:", "Lists of borders",
        "Lists of cities in Europe",
        "Lists of cities in Asia",
        "Lists of Chinatowns",
        "Lists of viscountcies",
        "Lists of counties",
        "Lists of counties in the United States",
        "Lists of Scottish counties by population",
        "Lists of countries and territories",
        "Lists of African Union members",
        "Lists of countries by mineral production",
        "Lists of former Soviet Republics",
        "Lists by country",
        "Lists of country-related topics",
        "Lists of Zambia-related topics",
        "Lists of divisions in Dorset",
        "Lists of non-sovereign nations",
        "List of outlines of countries in Asia",
        "Index of Quebec-related articles",
        "Lists of sovereign states and dependent territories",
        "Lists of political entities by century",
        "Lists of Spanish provinces",
        "Lists of Taiwanese counties and cities",
        "Lists of the Arab League",
        "Lists of time zones",
        "Lists of townlands of County Cork",
        "Lists of U.S. state topics",
        "Index of Alabama-related articles",
        "Index of Alaska-related articles",
        "Index of Arizona-related articles",
        "Index of Arkansas-related articles",
        "Index of California-related articles",
        "Index of Colorado-related articles",
        "Index of Connecticut-related articles",
        "Index of Delaware-related articles",
        "Index of Florida-related articles",
        "Index of Georgia-related articles",
        "Index of Idaho-related articles",
        "Index of Illinois-related articles",
        "Index of Indiana-related articles",
        "Index of Iowa-related articles",
        "Index of Kansas-related articles",
        "Index of Kentucky-related articles",
        "Index of Louisiana-related articles",
        "Index of Maine-related articles",
        "Index of Maryland-related articles",
        "Index of Massachusetts-related articles",
        "Index of Michigan-related articles",
        "Index of Minnesota-related articles",
        "Index of Mississippi-related articles",
        "Index of Missouri-related articles",
        "Index of Montana-related articles",
        "Index of Nebraska-related articles",
        "Index of Nevada-related articles",
        "Index of New Hampshire–related articles",
        "Index of New Jersey–related articles",
        "Index of New Mexico–related articles",
        "Index of New York (state)–related articles",
        "Index of North Carolina–related articles",
        "Index of North Dakota–related articles",
        "Index of Ohio-related articles",
        "Index of Oklahoma-related articles",
        "Lists of Oregon-related topics",
        "Index of Pennsylvania-related articles",
        "Index of Rhode Island–related articles",
        "Index of South Carolina–related articles",
        "Index of South Dakota–related articles",
        "Index of Tennessee-related articles",
        "Index of Texas-related articles",
        "Index of Utah-related articles",
        "Index of Vermont-related articles",
        "Index of Virginia-related articles",
        "Index of Washington (state)-related articles",
        "Index of Washington, D.C.–related articles",
        "Index of West Virginia–related articles",
        "Index of Wisconsin-related articles",
        "Index of Wyoming-related articles",
        "Ranked lists of Chilean regions",
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var i = 1
        binding = ToDoListBinding.inflate(inflater, container, false)

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_toDoListFragment_to_addNewFragment)
        }

        binding.btnAddNow.setOnClickListener {
            val current = "${LocalDateTime.now().hour}:${LocalDateTime.now().minute}"
            val textEntry = binding.editEntryNow.text.toString()
            if (textEntry.isNotEmpty()) {
                toDoList.add("$i. $textEntry At: $current")
                i++
            } else {
                Toast.makeText(activity, "Please make an entry!", Toast.LENGTH_LONG).show()
            }

            Toast.makeText(activity, "$textEntry Added!", Toast.LENGTH_SHORT).show()

        }
        binding.btnDeleteAll.setOnClickListener {
            toDoList.clear()
            toDoList.add("To Do List:")
            i = 1

            Toast.makeText(activity, "To do list has been cleared!", Toast.LENGTH_SHORT).show()
        }

        la = ListAdapter()
        la.submitList(toDoList)
        binding.toDoListRecycler.adapter = la

        return binding.root
    }
}