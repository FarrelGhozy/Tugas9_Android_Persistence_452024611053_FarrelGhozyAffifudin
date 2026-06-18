package com.example.tugas9.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas9.data.Note
import com.example.tugas9.data.NoteDao
import com.example.tugas9.data.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao: NoteDao = NoteDatabase.getInstance(application).noteDao()

    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    fun saveNote(title: String, content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.insert(Note(title = title, content = content))
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.update(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.delete(note)
        }
    }
}
