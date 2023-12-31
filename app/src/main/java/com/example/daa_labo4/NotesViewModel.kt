package com.example.daa_labo4;

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

    enum class SortType{
        CREATE,
        ETA
    }

    var sortEnum = MutableLiveData(SortType.CREATE)


}

class NotesViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
