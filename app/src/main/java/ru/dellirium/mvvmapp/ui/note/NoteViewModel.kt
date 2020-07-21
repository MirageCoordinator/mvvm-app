package ru.dellirium.mvvmapp.ui.note

import ru.dellirium.mvvmapp.data.model.Note
import ru.dellirium.mvvmapp.data.model.NoteResult
import ru.dellirium.mvvmapp.data.model.NotesRepository
import ru.dellirium.mvvmapp.ui.base.PropertyAwareMutableLiveData
import ru.dellirium.mvvmapp.ui.base.BaseViewModel

class NoteViewModel : BaseViewModel<NoteViewState.Data, NoteViewState>() {
    val note: PropertyAwareMutableLiveData<Note> by lazy {
        PropertyAwareMutableLiveData<Note>()
    }

    init {
        note.value = Note()
    }

    fun loadNote(noteId: String) {
        NotesRepository.getNoteById(noteId).observeForever { result ->
            result ?: return@observeForever
            when (result) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = NoteViewState(NoteViewState.Data(note = result.data as? Note))
                    note.value = viewStateLiveData.value?.run { data.note }
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = NoteViewState(error = result.error)
                }
            }
        }
    }

    fun deleteNote() {
        note.value?.let {
            NotesRepository.deleteNoteById(it.id).observeForever { result ->
                result ?: return@observeForever
                when (result) {
                    is NoteResult.Success<*> -> {
                        viewStateLiveData.value = NoteViewState(NoteViewState.Data(isDeleted = true))
                        note.value = viewStateLiveData.value?.run { data.note }
                    }
                    is NoteResult.Error -> {
                        viewStateLiveData.value = NoteViewState(error = result.error)
                    }
                }
            }
        }
    }

    fun changeColor(color: Int) {
        note.value?.let {
            it.color = color
        }
    }

    override fun onCleared() {
        note.value?.let {
            NotesRepository.saveNote(it).observeForever { result ->
                when (result) {
                    is NoteResult.Success<*> -> {
                        viewStateLiveData.value = NoteViewState(NoteViewState.Data(isDeleted = true))
                    }
                    is NoteResult.Error -> {
                        viewStateLiveData.value = NoteViewState(error = result.error)
                    }
                }
            }
        }
    }
}