package com.example.daa_labo4.db

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.daa_labo4.models.NoteAndSchedule;

@Dao
interface NaSDAO {
    fun deleteAll() {
        deleteAllNotes()
        deleteAllSchedules()
    }

    @Query("DELETE FROM Note") fun deleteAllNotes()
    @Query("DELETE FROM Schedule") fun deleteAllSchedules()

    @Query("SELECT COUNT(*) FROM Note") fun count(): LiveData<Int>

    @Query("SELECT * FROM Note AS n LEFT JOIN Schedule AS s ON s.ownerId = n.noteId ORDER BY s.date ASC")
    fun get(): LiveData<List<NoteAndSchedule>>

}