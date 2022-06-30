package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.dao.NotesDao
import com.example.noteapp.model.Notes

class NotesRepository(val dao:NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }

    fun getRedNotes(): LiveData<List<Notes>> = dao.getRedNotes()

    fun getYellowNotes(): LiveData<List<Notes>> = dao.getYellowNotes()

    fun getGreenNotes(): LiveData<List<Notes>> = dao.getGreenNotes()

    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }


}