package ru.zatsoft.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime

class MyFragment : Fragment(), OnFragmentDataListener {

    companion object {
        var notes = mutableListOf<Note>()
    }

    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private lateinit var adapter: CustomAdapter

    private var numberNote = 0
    private lateinit var rvList: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        val view = inflater.inflate(R.layout.fragment_my, container, false)
        val editTextId = view.findViewById<EditText>(R.id.edit_text_id)
        rvList = view.findViewById(R.id.rvList)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        adapter = CustomAdapter(notes)
        rvList.adapter = adapter
        rvList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvList.addItemDecoration(MyItemDecoration(requireContext(), R.drawable.divider))
        rvList.setHasFixedSize(true)
        adapter.setOnNoteClickListener(object :
            CustomAdapter.OnNoteClickListener {
            //            вызов 2 фрагмента
            override fun onNoteClick(note: Note, position: Int) {
                onData(note)
            }
        })

        btnAdd.setOnClickListener {
            val note = Note(
                numberNote++,
                editTextId.text.toString(),
                false,
                LocalDateTime.now()
            )
            notes.add(note)
            adapter.notifyDataSetChanged()
        }
        return view
    }

    override fun onData(note: Note) {
        val bundle = Bundle()
        bundle.putParcelable("note", note)
        val transaction = this.fragmentManager?.beginTransaction()
        val fragment2 = Fragment2()
        fragment2.arguments = bundle
        transaction?.replace(R.id.main, fragment2)
        transaction?.addToBackStack(null)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }

    override fun onResume() {
        super.onResume()
        val note = arguments?.getParcelable<Note>("newNote")
        if (note != null) {
            val key = arguments?.getInt("key")
            val index = search(notes, key!!)
            swap(notes, index, note)
            adapter = context?.let { CustomAdapter(notes) }!!
            rvList.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    fun search(notes: MutableList<Note>, key: Int): Int {
        var result = -1
        for (i in notes.indices) {
            if (key == notes[i].id) {
                result = i
            }
        }
        return result
    }

    fun swap(notes: MutableList<Note>, index: Int, note: Note) {
        notes.add(index + 1, note)
        notes.removeAt(index)
        notes[index] = note
    }
}