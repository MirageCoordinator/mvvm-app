package ru.dellirium.mvvmapp.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.dellirium.mvvmapp.R
import ru.dellirium.mvvmapp.databinding.ActivityMainBinding
import ru.dellirium.mvvmapp.databinding.ActivityNoteBinding
import ru.dellirium.mvvmapp.model.Note
import ru.dellirium.mvvmapp.ui.base.BaseActivity
import ru.dellirium.mvvmapp.ui.base.BaseViewModel

class NoteActivity : BaseActivity<Note?, NoteViewState>() {

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

    private var note: Note? = null
    override val viewModel: BaseViewModel<Note?, NoteViewState> by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    override val binding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_note) as ActivityNoteBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel as NoteViewModel?
        binding.viewModel?.run {
            setNote(intent.getParcelableExtra(EXTRA_NOTE))
        }
    }

    override fun renderData(data: Note?) {
        this.note = data
    }
}