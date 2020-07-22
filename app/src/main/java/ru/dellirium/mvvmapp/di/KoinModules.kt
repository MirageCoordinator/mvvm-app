package ru.dellirium.mvvmapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.dellirium.mvvmapp.data.model.NotesRepository
import ru.dellirium.mvvmapp.data.provider.DataProvider
import ru.dellirium.mvvmapp.data.provider.FirestoreDataProvider
import ru.dellirium.mvvmapp.ui.main.MainViewModel
import ru.dellirium.mvvmapp.ui.note.NoteViewModel
import ru.dellirium.mvvmapp.ui.splash.SplashViewModel

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirestoreDataProvider(get(), get()) } bind DataProvider::class
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}