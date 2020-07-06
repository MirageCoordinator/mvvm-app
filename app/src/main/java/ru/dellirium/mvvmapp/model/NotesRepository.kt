package ru.dellirium.mvvmapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.dellirium.mvvmapp.model.Note.Companion.BLUE
import ru.dellirium.mvvmapp.model.Note.Companion.RED
import ru.dellirium.mvvmapp.model.Note.Companion.VIOLET
import java.util.*

object NotesRepository {

    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes = mutableListOf(
            Note(
                    UUID.randomUUID().toString(),
                    "1",
                    "Lorem ipsum dolor set amet",
                    RED
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "2",
                    "consectetur adipiscing elit",
                    VIOLET
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "3",
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                    BLUE
            )
    )

    init {
        notesLiveData.value = notes
    }

    fun getNotes(): LiveData<List<Note>> {
        return notesLiveData
    }

    fun saveNote(note:Note){
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note){
        for (i in notes.indices){
            if(notes[i] == note){
                notes[i] = note
                return
            }
        }
        notes.add(note)
    }
}