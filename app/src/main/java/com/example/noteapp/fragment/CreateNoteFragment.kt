package com.example.noteapp.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentCreateNoteBinding
import com.example.noteapp.model.Notes
import com.example.noteapp.viewModel.NotesViewModel
import java.util.*

class CreateNoteFragment : Fragment() {

    lateinit var binding:FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater,container,false)

        binding.redFlag.setImageResource(R.drawable.ic_done)

        binding.redFlag.setOnClickListener {
            priority = "1"
            binding.redFlag.setImageResource(R.drawable.ic_done)
            binding.greenFlag.setImageResource(0)
            binding.yellowFlag.setImageResource(0)
        }

        binding.yellowFlag.setOnClickListener {
            priority = "2"
            binding.yellowFlag.setImageResource(R.drawable.ic_done)
            binding.greenFlag.setImageResource(0)
            binding.redFlag.setImageResource(0)
        }

        binding.greenFlag.setOnClickListener {
            priority = "3"
            binding.greenFlag.setImageResource(R.drawable.ic_done)
            binding.redFlag.setImageResource(0)
            binding.yellowFlag.setImageResource(0)
        }

        binding.fabSaveNote.setOnClickListener {
            createNote(it)
        }

        return binding.root
    }

    private fun createNote(it: View?){
        val title = binding.editTextNoteTitle.text.toString()
        val note = binding.editTextNote.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("d MMMM, yyyy ", d.time)

        val data = Notes(null, title = title, note = note, created_date = notesDate.toString(), updated_date = notesDate.toString(), priority)

        viewModel.addNotes(data)

        Toast.makeText(requireContext(),"Not başarıyla eklendi",Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_notesFragment)

    }

}