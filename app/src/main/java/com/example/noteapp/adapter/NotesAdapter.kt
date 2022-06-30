package com.example.noteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.databinding.CardNoteBinding
import com.example.noteapp.fragment.NotesFragmentDirections
import com.example.noteapp.model.Notes

class NotesAdapter(val context: Context, var noteList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    fun filtering(filteredList: ArrayList<Notes>){
        noteList = filteredList
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding: CardNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            CardNoteBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = noteList[position]

        holder.binding.cardNoteTitle.text = data.title.toString()
        holder.binding.cardNote.text = data.note.toString()
        holder.binding.cardNoteUpdatedDate.text = data.updated_date.toString()

        when(data.priority){
            "1" -> {
                holder.binding.cardFlag.setBackgroundResource(R.drawable.red_flag)
            }
            "2" -> {
                holder.binding.cardFlag.setBackgroundResource(R.drawable.yellow_flag)
            }
            "3" -> {
                holder.binding.cardFlag.setBackgroundResource(R.drawable.green_flag)
            }
        }

        holder.binding.root.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}