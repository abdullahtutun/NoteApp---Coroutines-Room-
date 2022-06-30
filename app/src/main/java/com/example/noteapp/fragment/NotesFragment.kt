package com.example.noteapp.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.adapter.NotesAdapter
import com.example.noteapp.databinding.FragmentNotesBinding
import com.example.noteapp.model.Notes
import com.example.noteapp.viewModel.NotesViewModel

class NotesFragment : Fragment() {

    lateinit var binding: FragmentNotesBinding
    val viewModel: NotesViewModel by viewModels()
    var myOldNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotesBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.layoutManager = layoutManager

        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
            binding.filterAll.setImageResource(R.drawable.ic_done)
            binding.filterGreen.setImageResource(0)
            binding.filterYellow.setImageResource(0)
            binding.filterRed.setImageResource(0)

            myOldNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(),notesList)
            binding.rvNotes.adapter = adapter
        })

        binding.filterAll.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
                binding.filterAll.setImageResource(R.drawable.ic_done)
                binding.filterGreen.setImageResource(0)
                binding.filterYellow.setImageResource(0)
                binding.filterRed.setImageResource(0)

                myOldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rvNotes.adapter = adapter
            })
        }

        binding.filterRed.setOnClickListener {
            viewModel.getRedNotes().observe(viewLifecycleOwner, { notesList ->
                binding.filterRed.setImageResource(R.drawable.ic_done)
                binding.filterGreen.setImageResource(0)
                binding.filterYellow.setImageResource(0)
                binding.filterAll.setImageResource(0)

                myOldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rvNotes.adapter = adapter
            })
        }

        binding.filterYellow.setOnClickListener {
            viewModel.getYellowNotes().observe(viewLifecycleOwner, { notesList ->
                binding.filterYellow.setImageResource(R.drawable.ic_done)
                binding.filterGreen.setImageResource(0)
                binding.filterAll.setImageResource(0)
                binding.filterRed.setImageResource(0)

                myOldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rvNotes.adapter = adapter
            })
        }

        binding.filterGreen.setOnClickListener {
            viewModel.getGreenNotes().observe(viewLifecycleOwner, { notesList ->
                binding.filterGreen.setImageResource(R.drawable.ic_done)
                binding.filterAll.setImageResource(0)
                binding.filterYellow.setImageResource(0)
                binding.filterRed.setImageResource(0)

                myOldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rvNotes.adapter = adapter
            })
        }

        binding.fabAddNote.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_notesFragment_to_createNoteFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as android.widget.SearchView
        searchView.queryHint = "Not arayın"

        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterNote(newText)

                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun filterNote(newText: String?){
        val filteredList = arrayListOf<Notes>()

        for (i in myOldNotes){
            if (i.title!!.contains(newText!!) || i.note!!.contains(newText!!)){
                filteredList.add(i)

            }
        }

        adapter.filtering(filteredList)
    }

}