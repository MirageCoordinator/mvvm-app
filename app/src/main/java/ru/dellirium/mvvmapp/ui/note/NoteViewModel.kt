package ru.dellirium.mvvmapp.ui.note

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dellirium.mvvmapp.model.Note
import ru.dellirium.mvvmapp.model.NoteResult
import ru.dellirium.mvvmapp.model.NotesRepository
import ru.dellirium.mvvmapp.ui.base.BaseViewModel

class NoteViewModel : BaseViewModel<Note?, NoteViewState>() {
    val note = MutableLiveData<Note>()

    fun setNote(note: Note?) {
        this.note.value = note
    }

    fun loadNote(noteId: Note) {
        NotesRepository.getNotes().observeForever {result ->
            result ?: return@observeForever
            when(result){
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = NoteViewState(note = result.data as? Note)
                }
//                is NoteResult.Error -> {
//                    viewStateLiveData.value = NoteViewState(error = result.error)
//                }
            }
        }
    }

    override fun onCleared() {
        note.value?.let { NotesRepository.saveNote(it) }
    }
}