package ru.dellirium.mvvmapp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ru.dellirium.mvvmapp.databinding.ItemNoteBinding

class NotesAdapter(val onItemClick: ((Note) -> Unit)? = null) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(vh: ViewHolder, pos: Int) {
        vh.binding.model = notes[pos]
        vh.setListener(notes[pos])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemNoteBinding = DataBindingUtil.bind(itemView)!!

        fun setListener(note: Note) {
            itemView.setOnClickListener {
                onItemClick?.invoke(note)
            }
        }
    }

}