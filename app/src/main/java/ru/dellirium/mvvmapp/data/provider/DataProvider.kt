package ru.dellirium.mvvmapp.data.provider

import androidx.lifecycle.LiveData
import ru.dellirium.mvvmapp.data.model.Note
import ru.dellirium.mvvmapp.data.model.NoteResult
import ru.dellirium.mvvmapp.data.model.User

interface DataProvider {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun deleteNote(noteId: String): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>

}