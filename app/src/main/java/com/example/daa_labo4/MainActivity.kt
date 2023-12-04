package com.example.daa_labo4

import com.example.daa_labo4.models.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    fun randomNote(): NoteAndSchedule {
        return NoteAndSchedule(Note.generateRandomNote(), Note.generateRandomSchedule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customAdapter = CustomRecyclerViewAdapter((application as DbApp).repository)

        val recyclerView: RecyclerView = findViewById(R.id.notes_recyclerView)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actions, menu)
        return true
    }
}

