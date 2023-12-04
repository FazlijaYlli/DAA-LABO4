package com.example.daa_labo4

import android.app.Application
import com.example.daa_labo4.db.NasDatabase
import com.example.daa_labo4.db.Repository

class DbApp : Application() {
    val repository by lazy {
        val database = NasDatabase.getDatabase(this)
        Repository(database.nasDao(), database.noteDao(), database.scheduleDao())
    }
}