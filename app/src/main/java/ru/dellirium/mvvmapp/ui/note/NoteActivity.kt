package ru.dellirium.mvvmapp.ui.note

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.dellirium.mvvmapp.R
import ru.dellirium.mvvmapp.data.model.Note
import ru.dellirium.mvvmapp.databinding.ActivityNoteBinding
import ru.dellirium.mvvmapp.ui.base.BaseActivity
import java.util.*

class NoteActivity : BaseActivity<NoteViewState.Data, NoteViewState>() {

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
    override val viewModel: NoteViewModel by viewModel()

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

        colorPicker.onColorClickListener = {
            viewModel.changeColor(it)
        }
    }

    override fun renderData(data: NoteViewState.Data) {
        if (data.isDeleted) finish()
        this.note = data.note
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = menuInflater.inflate(R.menu.note, menu)
            .let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> onBackPressed().let { true }
        R.id.palette -> togglePalette().let { true }
        R.id.delete -> deleteNote().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        AlertDialog.Builder(this)
                .setMessage("Are you sure?")
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.deleteNote()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
    }

    private fun togglePalette() {
        if(binding.colorPicker.isOpen) {
            binding.colorPicker.close()
        } else {
            binding.colorPicker.open()
        }
    }

    override fun onBackPressed() {
        if (binding.colorPicker.isOpen) {
            binding.colorPicker.close()
            return
        }
        super.onBackPressed()
    }
}