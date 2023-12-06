package com.example.daa_labo4.views

import com.example.daa_labo4.models.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daa_labo4.CustomRecyclerViewAdapter
import com.example.daa_labo4.DbApp
import com.example.daa_labo4.NotesViewModel
import com.example.daa_labo4.R

class MainActivity : AppCompatActivity() {

    fun randomNote(): NoteAndSchedule {
        return NoteAndSchedule(Note.generateRandomNote(), Note.generateRandomSchedule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        val customAdapter = CustomRecyclerViewAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.notes_recyclerView)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        (application as DbApp).repository.allNaS.observe(this) {
            customAdapter.noteList = it
            customAdapter.notifyDataSetChanged()
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actions, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val nvm = NotesViewModel((application as DbApp).repository)

        when (item.itemId) {
            R.id.Generate_item ->  nvm.generateANote()
            R.id.DeleteAll_item -> nvm.deleteAllNote()
            R.id.sort -> {
                //TODO : update la recyclerView
                nvm.sortNoteListByNearestDueDate()
            }
            R.id.creation_date_item -> {
                //TODO : update la recyclerView
                nvm.sortNoteListByCreationDate()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

