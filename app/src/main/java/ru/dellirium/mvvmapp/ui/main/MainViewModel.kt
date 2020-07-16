package ru.dellirium.mvvmapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.dellirium.mvvmapp.data.model.Note
import ru.dellirium.mvvmapp.data.model.NoteResult
import ru.dellirium.mvvmapp.data.model.NotesRepository
import ru.dellirium.mvvmapp.ui.base.BaseViewModel

class MainViewModel : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = Observer<NoteResult> { result ->
        result ?: return@Observer
        when(result) {
            is NoteResult.Success<*> -> {
                viewStateLiveData.value = MainViewState(notes = result.data as? List<Note>)
            }
            is NoteResult.Error -> {
                viewStateLiveData.value = MainViewState(error =  result.error)
            }
        }
    }

    private val repositoryNotes = NotesRepository.getNotes()

    init {
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        repositoryNotes.observeForever(notesObserver)
        super.onCleared()
    }

}