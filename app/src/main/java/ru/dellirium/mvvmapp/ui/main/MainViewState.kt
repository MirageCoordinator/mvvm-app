package ru.dellirium.mvvmapp.ui.main

import ru.dellirium.mvvmapp.model.Note
import ru.dellirium.mvvmapp.ui.base.BaseViewState

class MainViewState (
        val notes: List<Note>? = null,
        error: Throwable? = null) : BaseViewState<List<Note>?>(notes, error)