package ru.dellirium.mvvmapp.ui.splash

import ru.dellirium.mvvmapp.data.errors.NoAuthException
import ru.dellirium.mvvmapp.data.model.NotesRepository
import ru.dellirium.mvvmapp.ui.base.BaseViewModel

class SplashViewModel : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        NotesRepository.getCurrentUser().observeForever() {
            viewStateLiveData.value = it?.let {
                SplashViewState(authenticated = true)
            } ?: let {
                SplashViewState(error = NoAuthException())
            }
        }
    }
}