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


class Fragment2 : Fragment(), OnFragmentDataListener {

    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private var note: Note? = null
    private lateinit var editTextId: EditText

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        note = arguments?.getParcelable("note")
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        editTextId = view.findViewById(R.id.edit_text_id)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        editTextId.setText(note?.string)


        btnAdd.setOnClickListener {
            note?.string = editTextId.text.toString()
            onData(note!!)
        }
        return view
    }

    override fun onData(note: Note){
        val bundle = Bundle()
        bundle.putParcelable("newNote", note)
        bundle.putInt("key", note.id)
        val transaction = this.fragmentManager?.beginTransaction()
        val myFragment =  MyFragment()
        myFragment.arguments = bundle

        transaction?.replace(R.id.main,myFragment)
        transaction?.addToBackStack(null)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }
}