package ru.dellirium.mvvmapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dellirium.mvvmapp.model.Note
import ru.dellirium.mvvmapp.model.NotesRepository

class MainViewModel : ViewModel() {
    private var notesList: LiveData<List<Note>> = MutableLiveData()

    init {
        notesList = NotesRepository.getNotes()
    }

    fun getNotesList(): LiveData<List<Note>> = notesList

}