package ru.dellirium.mvvmapp.ui.note

import ru.dellirium.mvvmapp.data.model.Note
import ru.dellirium.mvvmapp.ui.base.BaseViewState

class NoteViewState(val note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)