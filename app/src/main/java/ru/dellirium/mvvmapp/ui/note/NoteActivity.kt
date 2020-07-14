package ru.dellirium.mvvmapp.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import ru.dellirium.mvvmapp.R
import ru.dellirium.mvvmapp.databinding.ActivityNoteBinding
import ru.dellirium.mvvmapp.model.Note
import ru.dellirium.mvvmapp.ui.base.BaseActivity
import ru.dellirium.mvvmapp.ui.base.BaseViewModel
import java.util.*

class NoteActivity : BaseActivity<Note?, NoteViewState>() {

    companion object {
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"
        private const val DATE_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, noteId: String? = null) = Intent(context, NoteActivity::class.java).run {
            noteId?.let {
                putExtra(EXTRA_NOTE, noteId)
            }
            context.startActivity(this)
        }
    }

    private var note: Note? = null
    override val viewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    override val binding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_note) as ActivityNoteBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        val noteId = intent.getStringExtra(EXTRA_NOTE)
        noteId?.let { id ->
            viewModel.loadNote(id)
        } ?: let {
            viewModel.note.value = Note(id = UUID.randomUUID().toString())
        }
    }

    override fun renderData(data: Note?) {
        this.note = data
    }
}