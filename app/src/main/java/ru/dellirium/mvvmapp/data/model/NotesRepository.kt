package ru.dellirium.mvvmapp.data.model

import ru.dellirium.mvvmapp.data.provider.FirestoreDataProvider
import ru.dellirium.mvvmapp.data.provider.DataProvider

object NotesRepository {
    private val PROVIDER: DataProvider = FirestoreDataProvider()

    fun getNotes() = PROVIDER.subscribeToAllNotes()
    fun saveNote(note: Note) = PROVIDER.saveNote(note)
    fun getNoteById(id: String) = PROVIDER.getNoteById(id)
    fun deleteNoteById(id: String) = PROVIDER.deleteNote(id)
    fun getCurrentUser() = PROVIDER.getCurrentUser()
}