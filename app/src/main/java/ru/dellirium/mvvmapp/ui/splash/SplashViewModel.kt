package ru.dellirium.mvvmapp.ui.splash

import ru.dellirium.mvvmapp.data.errors.NoAuthException
import ru.dellirium.mvvmapp.data.model.NotesRepository
import ru.dellirium.mvvmapp.ui.base.BaseViewModel

class SplashViewModel(val notesRepository: NotesRepository) : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        notesRepository.getCurrentUser().observeForever() {
            viewStateLiveData.value = it?.let {
                SplashViewState(authenticated = true)
            } ?: SplashViewState(error = NoAuthException())
        }
    }
}