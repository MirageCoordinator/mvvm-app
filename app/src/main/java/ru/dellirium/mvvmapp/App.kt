package ru.dellirium.mvvmapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.dellirium.mvvmapp.di.appModule
import ru.dellirium.mvvmapp.di.mainModule
import ru.dellirium.mvvmapp.di.noteModule
import ru.dellirium.mvvmapp.di.splashModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, splashModule, mainModule, noteModule)
        }
    }
}