package com.example.noteapp.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentEditNoteBinding
import com.example.noteapp.model.Notes
import com.example.noteapp.viewModel.NotesViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*

class EditNoteFragment : Fragment() {

    val oldNotes by navArgs<EditNoteFragmentArgs>()
    var priority: String = "1"
    lateinit var binding: FragmentEditNoteBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditNoteBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        binding.editTextEditNoteTitle.setText(oldNotes.data.title)
        binding.editTextEditNote.setText(oldNotes.data.note)
        binding.updatedDate.setText(oldNotes.data.updated_date)
        binding.createdDate.setText(oldNotes.data.created_date)

        when(oldNotes.data.priority){
            "1" -> {
                priority = "1"
                binding.editRedFlag.setImageResource(R.drawable.ic_done)
                binding.editGreenFlag.setImageResource(0)
                binding.editYellowFlag.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.editYellowFlag.setImageResource(R.drawable.ic_done)
                binding.editGreenFlag.setImageResource(0)
                binding.editRedFlag.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.editGreenFlag.setImageResource(R.drawable.ic_done)
                binding.editRedFlag.setImageResource(0)
                binding.editYellowFlag.setImageResource(0)
            }
        }

        binding.editRedFlag.setOnClickListener {
            priority = "1"
            binding.editRedFlag.setImageResource(R.drawable.ic_done)
            binding.editGreenFlag.setImageResource(0)
            binding.editYellowFlag.setImageResource(0)
        }

        binding.editYellowFlag.setOnClickListener {
            priority = "2"
            binding.editYellowFlag.setImageResource(R.drawable.ic_done)
            binding.editGreenFlag.setImageResource(0)
            binding.editRedFlag.setImageResource(0)
        }

        binding.editGreenFlag.setOnClickListener {
            priority = "3"
            binding.editGreenFlag.setImageResource(R.drawable.ic_done)
            binding.editRedFlag.setImageResource(0)
            binding.editYellowFlag.setImageResource(0)
        }

        binding.fabEditNote.setOnClickListener {
            updateNote(it)
        }

        return binding.root
    }

    private fun updateNote(it: View?){
        val title = binding.editTextEditNoteTitle.text.toString()
        val note = binding.editTextEditNote.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("d MMMM, yyyy ", d.time)

        val data = Notes(oldNotes.data.id, title = title, note = note, created_date = oldNotes.data.created_date.toString(), updated_date = notesDate.toString(), priority)

        Log.e("etiket","Title: $title note: $note")

        viewModel.updateNotes(data)

        Toast.makeText(requireContext(),"Not başarıyla güncellendi", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_notesFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteNote){

            Snackbar.make(binding.clEdit,
               "Silmek istiyor musunuz?", Snackbar.LENGTH_INDEFINITE)
                .setAction("Evet"){
                    viewModel.deleteNotes(oldNotes.data.id!!)

                    Navigation.findNavController(getView()!!).navigate(R.id.action_editNoteFragment_to_notesFragment)
                }.show()

        }
        return super.onOptionsItemSelected(item)
    }





















}