package com.example.daa_labo4.db

import androidx.lifecycle.LiveData
import com.example.daa_labo4.models.NoteAndSchedule
import kotlin.concurrent.thread

class Repository(private val nasDAO: NaSDAO, private val noteDAO: NoteDAO, private val scheduleDAO: ScheduleDAO) {
    var allNaS = nasDAO.get()
    val nasCount = nasDAO.count()

    fun insert(nas: NoteAndSchedule) {
        thread {
            noteDAO.insert(nas.note)
            if (nas.schedule != null) {
                scheduleDAO.insert(nas.schedule)
            }
        }
    }

    fun delete(nas: NoteAndSchedule) {
        thread {
            noteDAO.delete(nas.note)
            if (nas.schedule != null) {
                scheduleDAO.delete(nas.schedule)
            }
        }
    }

    fun deleteAll() {
        thread {
            nasDAO.deleteAll()
        }
    }
}