package ru.zatsoft.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ru.zatsoft.fragments.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CustomAdapter
    private lateinit var toolBar: Toolbar
    private lateinit var notes:MutableList<Note>
    private var numberNote = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolBar = binding.toolbarMain
        setSupportActionBar(toolBar)
        title = " "

        notes= mutableListOf(
        Note(
            numberNote++,
            binding.editTextId.text.toString(),
            false,
            Date()
        ))

        adapter = CustomAdapter(notes)
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.addItemDecoration(MyItemDecoration(this, R.drawable.divider))
        binding.rvList.setHasFixedSize(true)
        adapter.setOnNoteClickListener(
            object : CustomAdapter.OnNoteClickListener {
                override fun onNoteClick(note: Note, position: Int) {
                   println("------- position $position")

                }
            }
        )


        binding.btnAdd.setOnClickListener {
             val note = Note(
                numberNote++,
                binding.editTextId.text.toString(),
                false,
                Date()
            )
              notes.add(note)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exit)
            finish()
        return super.onOptionsItemSelected(item)
    }
}