package ru.dellirium.mvvmapp.ui.note

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dellirium.mvvmapp.model.Note
import ru.dellirium.mvvmapp.model.NotesRepository

class NoteViewModel : ViewModel() {
    val note = MutableLiveData<Note>()

    fun setNote(note: Note?) {
        this.note.value = note
    }

    override fun onCleared() {
        note.value?.let { NotesRepository.saveNote(it) }
    }
}