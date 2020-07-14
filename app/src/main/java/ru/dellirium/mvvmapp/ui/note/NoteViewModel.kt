package ru.dellirium.mvvmapp.ui.note

import ru.dellirium.mvvmapp.model.Note
import ru.dellirium.mvvmapp.model.NoteResult
import ru.dellirium.mvvmapp.model.NotesRepository
import ru.dellirium.mvvmapp.ui.base.PropertyAwareMutableLiveData
import ru.dellirium.mvvmapp.ui.base.BaseViewModel

class NoteViewModel : BaseViewModel<Note?, NoteViewState>() {
    val note: PropertyAwareMutableLiveData<Note> by lazy {
        PropertyAwareMutableLiveData<Note>()
    }

    fun loadNote(noteId: String) {
        NotesRepository.getNoteById(noteId).observeForever {result ->
            result ?: return@observeForever
            when(result){
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = NoteViewState(note = result.data as? Note)
                    note.value = viewStateLiveData.value!!.note
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = NoteViewState(error = result.error)
                }
            }
        }
    }

    override fun onCleared() {
        note.value?.let {
            NotesRepository.saveNote(it)
        }
    }
}