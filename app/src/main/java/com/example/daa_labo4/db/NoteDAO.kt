package com.example.daa_labo4.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.daa_labo4.models.Note
import com.example.daa_labo4.models.State


@Dao
interface NoteDAO {
    @Insert
    fun insert(note: Note) : Long

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM Note")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM Note WHERE noteId = :noteId")
    fun getNoteById(noteId: Long) : Note

    @Query("SELECT * FROM Note WHERE state = :state")
    fun getNoteByState(state: State) : LiveData<List<Note>>

    @Query("DELETE FROM Note") fun deleteAll()

    @Query("SELECT COUNT(*) FROM Note") fun count(): LiveData<Int>
}