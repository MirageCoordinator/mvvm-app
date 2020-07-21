package ru.dellirium.mvvmapp.ui.note

import ru.dellirium.mvvmapp.data.model.Note
import ru.dellirium.mvvmapp.ui.base.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data, error) {

    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}