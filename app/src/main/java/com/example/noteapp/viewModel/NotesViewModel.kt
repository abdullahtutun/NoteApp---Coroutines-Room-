package com.example.noteapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.noteapp.database.Database
import com.example.noteapp.model.Notes
import com.example.noteapp.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: NotesRepository

    init {
        val dao = Database.getDatabaseInstance(application).notesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes:Notes){
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun getRedNotes(): LiveData<List<Notes>> = repository.getRedNotes()

    fun getYellowNotes(): LiveData<List<Notes>> = repository.getYellowNotes()

    fun getGreenNotes(): LiveData<List<Notes>> = repository.getGreenNotes()

    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes:Notes) {
        repository.updateNotes(notes)
    }
}