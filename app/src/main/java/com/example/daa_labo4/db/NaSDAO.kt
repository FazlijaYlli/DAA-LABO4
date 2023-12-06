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

    @Query("SELECT * FROM Note")
    fun get(): LiveData<List<NoteAndSchedule>>

    @Query("SELECT * FROM Note LEFT JOIN Schedule ORDER BY date")
    fun getSortedETA(): LiveData<List<NoteAndSchedule>>

    @Query("SELECT * FROM Note ORDER BY creationDate")
    fun getSortedCreationDate(): LiveData<List<NoteAndSchedule>>
}