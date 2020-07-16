package ru.dellirium.mvvmapp.data.model

import ru.dellirium.mvvmapp.data.provider.FirestoreDataProvider
import ru.dellirium.mvvmapp.data.provider.RemoteDataProvider

object NotesRepository {
    private val remoteProvider: RemoteDataProvider = FirestoreDataProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
}