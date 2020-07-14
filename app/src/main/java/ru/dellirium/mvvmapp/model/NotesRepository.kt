package ru.dellirium.mvvmapp.model

import ru.dellirium.mvvmapp.provider.FirestoreDataProvider
import ru.dellirium.mvvmapp.provider.RemoteDataProvider

object NotesRepository {
    private val remoteProvider: RemoteDataProvider = FirestoreDataProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
}