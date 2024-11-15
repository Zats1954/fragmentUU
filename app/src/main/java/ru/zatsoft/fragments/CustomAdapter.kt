package ru.zatsoft.fragments

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.time.format.DateTimeFormatter

class CustomAdapter(private val notes: MutableList<Note>) :
    RecyclerView.Adapter<CustomAdapter.NoteViewHolder>() {

    private var onNoteClickListener: OnNoteClickListener? = null

    interface OnNoteClickListener {
        fun onNoteClick(note: Note, position: Int)
    }


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView? = itemView.findViewById(R.id.item_id)
        val noteText: TextView = itemView.findViewById(R.id.item_text)
        val dateText: TextView = itemView.findViewById(R.id.time_mark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount() = notes.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.id?.text = note.id.toString()
        holder.noteText.text = note.string
        val dateFormat = DateTimeFormatter.ofPattern(" HH:mm dd.MM.yyyy")
        holder.dateText.text = note.date.format(dateFormat).toString()
        holder.itemView.setOnClickListener {
            if (onNoteClickListener != null) {
                onNoteClickListener!!.onNoteClick(note, position)
            }
        }
    }

    fun setOnNoteClickListener(onNoteClickListener: OnNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener
    }
}