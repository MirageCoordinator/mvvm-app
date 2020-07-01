package ru.dellirium.mvvmapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import ru.dellirium.mvvmapp.R
import ru.dellirium.mvvmapp.databinding.ActivityMainBinding
import ru.dellirium.mvvmapp.model.NotesAdapter
import ru.dellirium.mvvmapp.ui.note.NoteActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val binding: ActivityMainBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main)

        with(binding) {
            lifecycleOwner = this@MainActivity
            viewModel = mainViewModel

            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
            recyclerView.adapter = NotesAdapter {
                NoteActivity.start(this@MainActivity, it)
            }

            fab.setOnClickListener {
                NoteActivity.start(this@MainActivity, null)
            }

            viewModel?.let { mainViewModel1 ->
                mainViewModel1.getNotesList().observe(this@MainActivity, Observer {
                    (recyclerView.adapter as NotesAdapter).notes = it
                })
            }
        }
    }
}
