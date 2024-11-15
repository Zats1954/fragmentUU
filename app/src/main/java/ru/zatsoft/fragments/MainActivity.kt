package ru.zatsoft.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ru.zatsoft.fragments.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnFragmentDataListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myFragment = MyFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main,myFragment).commit()

        toolBar = binding.toolbarMain
        setSupportActionBar(toolBar)
        title = " "
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

    override fun onData(note: Note) {
        val bundle = Bundle()
        bundle.putParcelable("note", note)
    }
}