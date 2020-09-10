package com.dev777popov.noteskotlingeek.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dev777popov.noteskotlingeek.R
import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.utils.getColor
import kotlinx.android.synthetic.main.item_notes_lists.view.*

class NotesRVAdapter(val onItemClick: ((Note) -> Unit)? = null) : RecyclerView.Adapter<NotesRVAdapter.MyViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notes_lists,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(notes[position])

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(itemView) {
            tv_title.text = note.title
            tv_text.text = note.text

            setBackgroundColor(ContextCompat.getColor(itemView.context, getColor(note.color)))

            itemView.setOnClickListener {
                onItemClick?.invoke(note)
            }
        }
    }
}