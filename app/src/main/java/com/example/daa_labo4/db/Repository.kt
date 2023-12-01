package com.example.daa_labo4.db

import NaSDAO
import com.example.daa_labo4.models.NoteAndSchedule
import kotlin.concurrent.thread

class Repository(private val nasDAO: NaSDAO) {
    val allNaS = nasDAO.getAllNaS()
    val nasCount = nasDAO.getCount()

    fun insert(nas: NoteAndSchedule) {
        thread {
            nasDAO.insert(nas)
        }
    }

    fun delete(nas: NoteAndSchedule) {
        thread {
            nasDAO.delete(nas)
        }
    }

    fun deleteAll() {
        thread {
            nasDAO.deleteAll()
        }
    }
}