package com.example.daa_labo4.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class NoteAndSchedule (
    @Embedded val note: Note,
    @Relation(
        parentColumn = "noteId",
        entityColumn = "ownerId"
    )
    val schedule: Schedule?
)
