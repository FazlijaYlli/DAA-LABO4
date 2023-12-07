package com.example.daa_labo4.views

import com.example.daa_labo4.models.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.daa_labo4.DbApp
import com.example.daa_labo4.NotesViewModel
import com.example.daa_labo4.NotesViewModelFactory
import com.example.daa_labo4.R

class MainActivity : AppCompatActivity() {

    private val noteViewModel: NotesViewModel by viewModels {
        NotesViewModelFactory((application as DbApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actions, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.Generate_item -> noteViewModel.generateANote()
            R.id.DeleteAll_item -> noteViewModel.deleteAllNote()
            R.id.sort -> noteViewModel.sortEnum.value = NotesViewModel.SortType.ETA
            R.id.creation_date_item -> noteViewModel.sortEnum.value = NotesViewModel.SortType.CREATE

        }
        return super.onOptionsItemSelected(item)
    }
}

