package ru.dellirium.mvvmapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dellirium.mvvmapp.model.Note
import ru.dellirium.mvvmapp.model.NotesRepository

class MainViewModel : ViewModel() {
    private val counter: MutableLiveData<Int?> = MutableLiveData()
    private val notesList: MutableLiveData<List<Note>> = MutableLiveData()

    init {
        counter.value = 0
        notesList.value = NotesRepository().getNotes()
    }

    fun valueCounter(): LiveData<Int?> = counter

    fun getNotesList(): LiveData<List<Note>> = notesList

    fun onClick() {
        counter.value = counter.value?.plus(1)
    }

}