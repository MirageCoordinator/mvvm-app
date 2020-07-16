package ru.dellirium.mvvmapp.ui.splash

import ru.dellirium.mvvmapp.ui.base.BaseViewState

class SplashViewState(authenticated: Boolean? = null, error: Throwable? = null)
    : BaseViewState<Boolean?>(authenticated, error)