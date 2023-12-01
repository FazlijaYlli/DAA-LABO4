package com.example.daa_labo4;

import androidx.lifecycle.ViewModel
import com.example.daa_labo4.db.Repository
import com.example.daa_labo4.models.Note
import com.example.daa_labo4.models.NoteAndSchedule

public class NotesViewModel(private val repository: Repository) : ViewModel() {
    val allNotes = repository.allNaS
    val countNotes = repository.nasCount

    fun generateANote() {
        val nas = NoteAndSchedule(Note.generateRandomNote(), Note.generateRandomSchedule())
        repository.insert(nas)
    }

    fun deleteAllNote() {
        repository.deleteAll()
    }
}
