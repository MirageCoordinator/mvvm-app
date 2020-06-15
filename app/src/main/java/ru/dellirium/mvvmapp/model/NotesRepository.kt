package ru.dellirium.mvvmapp.model

class NotesRepository {

    private val notes: List<Note> = listOf(
            Note(
                    "1",
                    "Lorem ipsum dolor set amet"
            ),
            Note(
                    "2",
                    "consectetur adipiscing elit"
            ),
            Note(
                    "3",
                    " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
            )
    )

    fun getNotes(): List<Note> {
        return notes
    }
}