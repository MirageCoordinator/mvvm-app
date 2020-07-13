package ru.dellirium.mvvmapp.provider

import androidx.lifecycle.LiveData
import ru.dellirium.mvvmapp.model.Note
import ru.dellirium.mvvmapp.model.NoteResult

interface RemoteDataProvider {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
}