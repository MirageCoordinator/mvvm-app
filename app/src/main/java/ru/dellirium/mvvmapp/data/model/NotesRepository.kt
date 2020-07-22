package ru.dellirium.mvvmapp.data.model

import ru.dellirium.mvvmapp.data.provider.DataProvider

class NotesRepository(val provider: DataProvider) {

    fun getNotes() = provider.subscribeToAllNotes()
    fun saveNote(note: Note) = provider.saveNote(note)
    fun getNoteById(id: String) = provider.getNoteById(id)
    fun deleteNoteById(id: String) = provider.deleteNote(id)
    fun getCurrentUser() = provider.getCurrentUser()
}