package com.example.daa_labo4.db

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*
import com.example.daa_labo4.models.Schedule


@Dao
interface ScheduleDAO {
    @Insert
    fun insert(schedule: Schedule) : Long

    @Update
    fun update(schedule: Schedule)

    @Delete
    fun delete(schedule:Schedule)

    @Query("DELETE FROM Schedule")
    fun deleteAll()

    @Query("SELECT * FROM Schedule")
    fun getAllSchedules() : LiveData<List<Schedule>>

    @Query("SELECT * FROM Schedule WHERE scheduleId = :scheduleId")
    fun getNoteById(scheduleId: Long) : Schedule
}