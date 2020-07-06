package ru.dellirium.mvvmapp.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.dellirium.mvvmapp.R
import ru.dellirium.mvvmapp.databinding.ActivityNoteBinding
import ru.dellirium.mvvmapp.model.Note

class NoteActivity : AppCompatActivity() {

    companion object {
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"
        private const val DATE_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, note: Note?) = Intent(context, NoteActivity::class.java).run {
            note?.let {
                putExtra(EXTRA_NOTE, note)
            }
            context.startActivity(this)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        val binding: ActivityNoteBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_note)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.viewModel?.run {
            setNote(intent.getParcelableExtra(EXTRA_NOTE))
        }

    }
}