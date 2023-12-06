package com.example.daa_labo4

import com.example.daa_labo4.models.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    fun randomNote(): NoteAndSchedule {
        return NoteAndSchedule(Note.generateRandomNote(), Note.generateRandomSchedule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customAdapter = CustomRecyclerViewAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.notes_recyclerView)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        (application as DbApp).repository.allNaS.observe(this) {
            customAdapter.noteList = it
            customAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actions, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val nvm = NotesViewModel((application as DbApp).repository);
        when (item.itemId) {
            R.id.Generate_item ->  nvm.generateANote()
            R.id.DeleteAll_item -> nvm.deleteAllNote()
            R.id.sort ->  {} // todo: sort notes par creation dans le fragment
            R.id.creation_date_item -> {} // todo: sort notes par date prévue dans le fragment
        }
        return super.onOptionsItemSelected(item)
    }
}

