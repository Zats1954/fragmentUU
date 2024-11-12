package ru.zatsoft.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val notes:MutableList<Note>):
    RecyclerView.Adapter<CustomAdapter.NoteViewHolder>() {

        private var onNoteClickListener: OnNoteClickListener? =null

        interface OnNoteClickListener{
            fun onNoteClick(note: Note, position: Int)
        }


    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var id: TextView? = itemView.findViewById(R.id.item_id)
        val noteText: TextView = itemView.findViewById(R.id.item_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        println("--- onCreateViewHolder")
        return NoteViewHolder(itemView)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.id?.text = note.id.toString()
        holder.noteText.text = note.string
//        holder.itemView.setOnClickListener {
//            if(onNoteClickListener != null){
//                onNoteClickListener!!.onNoteClick(note,position)
//            }
//        }
    }

    fun setOnNoteClickListener(onNoteClickListener: OnNoteClickListener){
        this.onNoteClickListener = onNoteClickListener
    }
}