package ru.zatsoft.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class MyFragment : Fragment() {

    private lateinit var adapter: CustomAdapter
    private var notes = mutableListOf<Note>( )
    private var numberNote = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editTextId = view.findViewById<EditText>(R.id.edit_text_id)
        val rvList = view.findViewById<RecyclerView>(R.id.rvList)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        adapter = CustomAdapter(notes)
        rvList.adapter = adapter
        rvList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvList.addItemDecoration(MyItemDecoration(requireContext(), R.drawable.divider))
        rvList.setHasFixedSize(true)

        btnAdd.setOnClickListener {
            val note = Note(
                numberNote++,
                editTextId.text.toString(),
                false,
                Date()
            )
            notes.add(note)
            adapter.notifyDataSetChanged()
        }
    }
}